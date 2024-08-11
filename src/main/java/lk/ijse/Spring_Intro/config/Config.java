package lk.ijse.Spring_Intro.config;

import lk.ijse.Spring_Intro.aop.Transaction;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {Transaction.class, Long.class})
public class Config {
}
