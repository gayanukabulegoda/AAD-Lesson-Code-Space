package lk.ijse.Spring_Intro.config;

import lk.ijse.Spring_Intro.beans.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ComponentScan(basePackageClasses = {Customer.class})
@ComponentScan(basePackageClasses = {Test.class})
public class Config {
    // if configurations there, they need to place here
}
