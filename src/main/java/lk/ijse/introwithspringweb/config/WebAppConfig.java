package lk.ijse.introwithspringweb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.introwithspringweb")
@EnableWebMvc
public class WebAppConfig {
}
