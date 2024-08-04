package lk.ijse.Spring_Intro.dep;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Primary
public class Girl2 implements GoodGirl {
    @Override
    public void love() {
        System.out.println("Test Girl2");
    }
}
