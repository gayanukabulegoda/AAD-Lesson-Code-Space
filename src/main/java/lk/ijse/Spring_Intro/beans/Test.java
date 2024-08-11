package lk.ijse.Spring_Intro.beans;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("TestBean")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Test implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory is : " + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name is : " + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean destroyed");
    }

    // we can use below custom method to call some statement prior to dispose the bean
    // (is not in Spring Spec. This is a custom library add via dependency -> Jakarta Annotations)
    @PreDestroy
    public void preDestroyTest() {
        System.out.println("Pre Destroy Test");
    };

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContext is : " + applicationContext);
    }
}
