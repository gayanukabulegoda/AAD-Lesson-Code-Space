package lk.ijse.SpringBootNoteTaker.service;

import lk.ijse.SpringBootNoteTaker.customObj.UserErrorResponse;
import lk.ijse.SpringBootNoteTaker.customObj.UserResponse;
import lk.ijse.SpringBootNoteTaker.dto.impl.UserDTO;
import lk.ijse.SpringBootNoteTaker.entity.UserEntity;
import lk.ijse.SpringBootNoteTaker.exception.DataPersistFailedException;
import lk.ijse.SpringBootNoteTaker.exception.UserNotFoundException;
import lk.ijse.SpringBootNoteTaker.repository.UserRepository;
import lk.ijse.SpringBootNoteTaker.util.AppUtil;
import lk.ijse.SpringBootNoteTaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapping mapping;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Mapping mapping) {
        this.userRepository = userRepository;
        this.mapping = mapping;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        UserEntity savedUser = userRepository.save(mapping.convertToUserEntity(userDTO));
        if (savedUser == null && savedUser.getUserId() == null) {
            throw new DataPersistFailedException("User not saved");
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userRepository.findById(userDTO.getUserId());
        if (!tmpUser.isPresent()) throw new UserNotFoundException("User not found");
        else {
            UserEntity userEntity = tmpUser.get();
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setProfilePic(userDTO.getProfilePic());
            // bcz of the @Transactional annotation, we don't need to call the save method
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> selectedUser = userRepository.findById(userId);
        if (!selectedUser.isPresent()) {
            throw new UserNotFoundException("User not found");
        } else userRepository.deleteById(userId);
    }

    @Override
    public UserResponse getSelectedUser(String userId) {
        return (userRepository.existsById(userId))
                ? mapping.convertToUserDTO(userRepository.getReferenceById(userId))
                : new UserErrorResponse(0, "User not found");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapping.convertToUserDTO(userRepository.findAll());
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
