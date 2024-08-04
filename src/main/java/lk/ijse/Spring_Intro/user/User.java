package lk.ijse.Spring_Intro.user;

import jakarta.annotation.PostConstruct;
import lk.ijse.Spring_Intro.dep.GoodGirl;
import lk.ijse.Spring_Intro.dep.Wow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class User {
    @Autowired
    @Qualifier("Long hair")
    @Wow
    private GoodGirl goodGirl;

    @PostConstruct
    public void init() {
        goodGirl.love();
    }
}
