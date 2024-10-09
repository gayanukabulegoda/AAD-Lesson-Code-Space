package lk.ijse.SpringBootNoteTaker.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String createNoteId() {
        return "NOTE : " + UUID.randomUUID();
    }
    public static String createUserId() {
        return "USER : " + UUID.randomUUID();
    }
    public static String toBase64ProfilePic(MultipartFile profilePic) {
        String profilePicBase64 = null;
        try {
            byte [] profilePicByteCollection = profilePic.getBytes();
            profilePicBase64 =  Base64.getEncoder().encodeToString(profilePicByteCollection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profilePicBase64;
    }
}
