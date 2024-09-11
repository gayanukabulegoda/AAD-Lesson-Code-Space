package lk.ijse.notetaker.service;

import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.entity.UserEntity;
import lk.ijse.notetaker.exception.UserNotFoundException;
import lk.ijse.notetaker.repository.UserRepository;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        UserEntity savedUser = userRepository.save(mapping.convertToUserEntity(userDTO));
        return (savedUser != null && savedUser.getUserId() != null)
                ? "User Saved Successfully"
                : "User Save Failed";
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userRepository.findById(userDTO.getUserId());
        if (!tmpUser.isPresent()) throw new UserNotFoundException();
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
    public boolean deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        } else {
            userRepository.deleteById(userId);
            return true;
        }
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
//        if (userRepository.existsById(userId)) {
//            return mapping.convertToUserDTO(userRepository.getReferenceById(userId));
//        } else return null;

        // using the custom method getUserEntitiesByUserId from the UserRepository
        return (userRepository.existsById(userId))
                ? mapping.convertToUserDTO(userRepository.getUserEntitiesByUserId(userId))
                : null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapping.convertToUserDTO(userRepository.findAll());
    }
}
