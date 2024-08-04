package lk.ijse.Spring_Intro.dep;

import org.springframework.stereotype.Component;

@Component
public class Girl1 implements GoodGirl {
    @Override
    public void love() {
        System.out.println("Test Girl 1");
    }
}
