package lk.ijse.SpringBootNoteTaker.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.SpringBootNoteTaker.service.JWTService;
import lk.ijse.SpringBootNoteTaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JWTConfig extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String initToken = request.getHeader("Authorization");
        String userEmail;
        String jwtToken;

        // Initial Validation
        if (StringUtils.isEmpty(initToken) || !initToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Token Extraction
        jwtToken = initToken.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);

        // User Email Validation
        if (!StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details based on email
            UserDetails loadedUser = userService.userDetailsService().loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwtToken, loadedUser)) {
                SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetails(request));
                emptyContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(emptyContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}
