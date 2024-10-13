package lk.ijse.SpringBootNoteTaker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Value("${spring.jpa.security.user.name}") // value injection from application.properties
    String username;
    @Value("${spring.jpa.security.user.password}")
    String password;
    @Value("${spring.jpa.security.user.roles}")
    String role;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // authenticate using HTTP Basic Authentication
        return http.build();
    }

    @Bean // create an InMemoryUserDetailsManager bean inorder to store users in memory
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails principleUser = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles(role)
                .build();
        return new InMemoryUserDetailsManager(principleUser);
    }
}
