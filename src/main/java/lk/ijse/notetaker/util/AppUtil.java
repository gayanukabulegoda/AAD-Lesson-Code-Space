package lk.ijse.notetaker.util;

import java.util.UUID;

public class AppUtil {
    public static String createNoteId() {
        return "NOTE : " + UUID.randomUUID();
    }
    public static String createUserId() {
        return "USER : " + UUID.randomUUID();
    }
}
