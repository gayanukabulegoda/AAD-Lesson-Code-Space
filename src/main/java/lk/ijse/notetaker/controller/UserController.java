package lk.ijse.notetaker.controller;

import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.exception.UserNotFoundException;
import lk.ijse.notetaker.service.UserService;
import lk.ijse.notetaker.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        String saveStatus = userService.saveUser(buildUserDTO);
        if (saveStatus.contains("User Saved Successfully")) return new ResponseEntity<>(HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        return (userService.deleteUser(userId))
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getSelectedUser(@PathVariable("id") String userId) {
        UserDTO selectedUser = userService.getSelectedUser(userId);
        return (selectedUser != null)
                ? new ResponseEntity<>(selectedUser, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser(
            @PathVariable("id") String userId,
            @RequestPart("updateFirstName") String updateFirstName,
            @RequestPart("updateLastName") String updateLastName,
            @RequestPart("updateEmail") String updateEmail,
            @RequestPart("updatePassword") String updatePassword,
            @RequestPart("updateProfilePic") String updateProfilePic
    ) {
        try {
            String base64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic);
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setUserId(userId);
            buildUserDTO.setFirstName(updateFirstName);
            buildUserDTO.setLastName(updateLastName);
            buildUserDTO.setEmail(updateEmail);
            buildUserDTO.setPassword(updatePassword);
            buildUserDTO.setProfilePic(base64ProfilePic);
            userService.updateUser(buildUserDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
