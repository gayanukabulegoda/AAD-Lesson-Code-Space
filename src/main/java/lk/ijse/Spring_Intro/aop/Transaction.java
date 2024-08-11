package lk.ijse.Spring_Intro.aop;

import org.springframework.stereotype.Component;

@Component("transaction")
public class Transaction {
    public void startTransaction() {
        System.out.println("Starting Transaction");
    }

    public void endTransaction() {
        System.out.println("Ending Transaction");
    }
}
