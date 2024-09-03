package lk.ijse.notetaker.controller;

import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.service.UserService;
import lk.ijse.notetaker.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("health")
    public String healthCheck() {
        return "UserController is Running";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser(
            @RequestPart("firstname") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") String profilePic
    ) {
        // Handle profile picture
        String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);
        // Build the user object
        UserDTO buildUserDTO = new UserDTO();
        buildUserDTO.setFirstName(firstName);
        buildUserDTO.setLastName(lastName);
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        buildUserDTO.setProfilePic(base64ProfilePic);
        // Send to the service layer
        return new ResponseEntity<>(userService.saveUser(buildUserDTO), HttpStatus.CREATED);
    }
}
