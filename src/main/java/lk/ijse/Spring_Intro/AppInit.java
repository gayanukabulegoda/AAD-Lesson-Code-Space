package lk.ijse.Spring_Intro;

import lk.ijse.Spring_Intro.beans.Test;
import lk.ijse.Spring_Intro.config.Config;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInit {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class);
        ctx.refresh();

        // ConfigurableBeanFactory beanFactory = ctx.getBeanFactory();
        // boolean isSingletonCustomer = beanFactory.isSingleton("customer");

        Test test = (Test) ctx.getBean("TestBean");
        ConfigurableBeanFactory beanFactory = ctx.getBeanFactory();
        boolean isSingletonTest = beanFactory.isSingleton("TestBean");
        System.out.println(test);
        System.out.println("Is test singleton: "+isSingletonTest);
        ctx.registerShutdownHook();
    }
}
