package lk.ijse.notetaker.service;

import lk.ijse.notetaker.customObj.UserResponse;
import lk.ijse.notetaker.dto.impl.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

// If we include @Service annotation, no need to use @RequiredArgsConstructor in controller class
@Service
public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}
