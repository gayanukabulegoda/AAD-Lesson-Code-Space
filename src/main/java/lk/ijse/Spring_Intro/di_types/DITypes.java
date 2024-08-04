package lk.ijse.Spring_Intro.di_types;

import jakarta.annotation.PostConstruct;
import lk.ijse.Spring_Intro.dep.GoodGirl;
import lk.ijse.Spring_Intro.dep.Wow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class DITypes {

    // ------- (01) Field Injection -------
    @Component
    public static class FieldInjection {
        @Autowired
        @Qualifier("Long hair")
        @Wow
        private GoodGirl goodGirl;

        @PostConstruct
        public void init() {
            goodGirl.love();
            System.out.println("FieldInjection init invoked");
        }
    }

    // ------- (02) Constructor Injection -------
    @Component
    public static class ConstructorInjection {
        @Qualifier("Long hair")
        private final GoodGirl goodGirl;

        @Autowired
        @Wow
        public ConstructorInjection(GoodGirl goodGirl) {
            this.goodGirl = goodGirl;
        }

        @PostConstruct
        public void init() {
            goodGirl.love();
            System.out.println("ConstructorInjection init invoked");
        }
    }

    // ------- (03) Setter Injection -------
    @Component
    public static class SetterInjection {
        @Qualifier("Long hair")
        private GoodGirl goodGirl;

        @Autowired
        @Wow
        public void setGoodGirl(GoodGirl goodGirl) {
            this.goodGirl = goodGirl;
        }

        @PostConstruct
        public void init() {
            goodGirl.love();
            System.out.println("SetterInjection init invoked");
        }
    }
}
