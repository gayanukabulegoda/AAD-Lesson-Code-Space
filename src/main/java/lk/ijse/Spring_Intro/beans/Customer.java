package lk.ijse.Spring_Intro.beans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("customer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Customer {
    public Customer() {
        System.out.println("Hello Customer");
    }
}
