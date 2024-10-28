package lk.ijse.SpringBootNoteTaker.service;

import lk.ijse.SpringBootNoteTaker.dto.impl.UserDTO;
import lk.ijse.SpringBootNoteTaker.entity.UserEntity;
import lk.ijse.SpringBootNoteTaker.jwtmodels.JWTAuthResponse;
import lk.ijse.SpringBootNoteTaker.jwtmodels.SignIn;
import lk.ijse.SpringBootNoteTaker.repository.UserRepository;
import lk.ijse.SpringBootNoteTaker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final Mapping mapping;
    //utils
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var userByEmail = userRepository.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JWTAuthResponse.builder().token(generatedToken).build() ;
    }

    @Override
    public JWTAuthResponse signUp(UserDTO signUpUser) {
        UserEntity userEntity = mapping.convertToUserEntity(signUpUser);
        var savedUser = userRepository.save(userEntity);
        var genToken = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(genToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        var userName = jwtService.extractUsername(accessToken);
        var userEntity =
                userRepository.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
