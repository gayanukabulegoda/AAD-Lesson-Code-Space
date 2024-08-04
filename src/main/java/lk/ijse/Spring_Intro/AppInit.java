package lk.ijse.Spring_Intro;

import lk.ijse.Spring_Intro.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInit {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class);
        ctx.refresh();
        ctx.registerShutdownHook(); // in-order to shut-down gracefully except using .close()
    }
}
