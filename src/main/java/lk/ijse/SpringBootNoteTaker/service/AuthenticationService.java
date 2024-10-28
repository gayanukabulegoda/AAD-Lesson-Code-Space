package lk.ijse.SpringBootNoteTaker.service;

import lk.ijse.SpringBootNoteTaker.dto.impl.UserDTO;
import lk.ijse.SpringBootNoteTaker.jwtmodels.JWTAuthResponse;
import lk.ijse.SpringBootNoteTaker.jwtmodels.SignIn;

public interface AuthenticationService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO signUp);
    JWTAuthResponse refreshToken(String accessToken);
}