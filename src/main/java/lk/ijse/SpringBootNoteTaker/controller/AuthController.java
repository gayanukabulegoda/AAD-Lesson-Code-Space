package lk.ijse.SpringBootNoteTaker.controller;

import lk.ijse.SpringBootNoteTaker.dto.impl.UserDTO;
import lk.ijse.SpringBootNoteTaker.exception.DataPersistFailedException;
import lk.ijse.SpringBootNoteTaker.jwtmodels.JWTAuthResponse;
import lk.ijse.SpringBootNoteTaker.jwtmodels.SignIn;
import lk.ijse.SpringBootNoteTaker.service.AuthenticationService;
import lk.ijse.SpringBootNoteTaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JWTAuthResponse> signUp(
            @RequestPart("firstname") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic,
            @RequestPart("role") String role) {
        try {
            // Handle profile picture
            String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);
            // Build the user object
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setUserId(AppUtil.createUserId());
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(passwordEncoder.encode(password));
            buildUserDTO.setProfilePic(base64ProfilePic);
            buildUserDTO.setRole(role);
            // Send to the service layer
            return ResponseEntity.ok(authenticationService.signUp(buildUserDTO));
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
