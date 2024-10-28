package lk.ijse.SpringBootNoteTaker.service;

import lk.ijse.SpringBootNoteTaker.customObj.UserResponse;
import lk.ijse.SpringBootNoteTaker.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
    UserDetailsService userDetailsService();
}
