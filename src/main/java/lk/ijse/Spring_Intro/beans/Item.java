package lk.ijse.Spring_Intro.beans;

import org.springframework.stereotype.Component;

@Component
public class Item {
    public Item() {
        System.out.println("Hello Item");
    }
}
