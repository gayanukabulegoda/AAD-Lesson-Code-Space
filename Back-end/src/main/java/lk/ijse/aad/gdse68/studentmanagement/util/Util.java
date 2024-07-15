package lk.ijse.aad.gdse68.studentmanagement.util;

import java.util.UUID;

public class Util {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
