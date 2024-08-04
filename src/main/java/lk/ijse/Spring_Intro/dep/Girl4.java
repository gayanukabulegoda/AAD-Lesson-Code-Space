package lk.ijse.Spring_Intro.dep;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Long hair")
@Wow
public class Girl4 implements GoodGirl {
    @Override
    public void love() {
        System.out.println("Test Girl4");
    }
}
