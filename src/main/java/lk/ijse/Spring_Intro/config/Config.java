package lk.ijse.Spring_Intro.config;

import lk.ijse.Spring_Intro.beans.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "lk.ijse.Spring_Intro")
public class Config {
    // if configurations there, they need to place here

    @Bean
    public Order order() {
        return new Order();
    }
}
