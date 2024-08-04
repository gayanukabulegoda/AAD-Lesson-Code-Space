package lk.ijse.Spring_Intro.dep;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Long hair")
public class Girl3 implements GoodGirl {
    @Override
    public void love() {
        System.out.println("Test Girl3");
    }
}
