package lk.ijse.Spring_Intro;

import lk.ijse.Spring_Intro.aop.Transaction;
import lk.ijse.Spring_Intro.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInit {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class);
        ctx.refresh();
        Transaction transaction = (Transaction)ctx.getBean("transaction");
        transaction.startTransaction();
        transaction.endTransaction();
        ctx.registerShutdownHook();
    }
}
