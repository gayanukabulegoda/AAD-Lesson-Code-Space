package lk.ijse.Spring_Intro.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class Logs {
    @Before("execution(public void startTransaction())") // import from aspectj annotation
    public void logsForStartTransaction(){
        System.out.println("Logs for Transaction started");
    }

    @After("execution(public void endTransaction())")
    public void logsForEndTransaction(){
        System.out.println("Logs for Transaction ended");
    }
}
