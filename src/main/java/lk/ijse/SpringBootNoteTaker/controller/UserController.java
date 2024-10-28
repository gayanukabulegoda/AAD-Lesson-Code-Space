package lk.ijse.SpringBootNoteTaker.controller;

import lk.ijse.SpringBootNoteTaker.customObj.UserResponse;
import lk.ijse.SpringBootNoteTaker.dto.impl.UserDTO;
import lk.ijse.SpringBootNoteTaker.exception.UserNotFoundException;
import lk.ijse.SpringBootNoteTaker.service.UserService;
import lk.ijse.SpringBootNoteTaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getSelectedUser(@PathVariable("id") String userId) {
        return userService.getSelectedUser(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
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
            @RequestPart("updateProfilePic") MultipartFile updateProfilePic
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
