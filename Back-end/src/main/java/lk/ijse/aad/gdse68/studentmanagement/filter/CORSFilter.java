package lk.ijse.aad.gdse68.studentmanagement.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CORSFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("CORSFilter");

        String origin = getServletContext().getInitParameter("origin");

        if (origin.contains(getServletContext().getInitParameter("origin"))) {
            res.setHeader("Access-Control-Allow-Origin", origin); // allow the mentioned domain (domain URL)
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS"); // mention http methods to be allowed
            res.setHeader("Access-Control-Allow-Headers", "Content-Type");
            res.setHeader("Access-Control-Expose-Headers", "Content-Type");
        }
        chain.doFilter(req, res);
    }
}
