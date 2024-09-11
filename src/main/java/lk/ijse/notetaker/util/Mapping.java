package lk.ijse.notetaker.util;

import lk.ijse.notetaker.dto.impl.NoteDTO;
import lk.ijse.notetaker.dto.impl.UserDTO;
import lk.ijse.notetaker.entity.NoteEntity;
import lk.ijse.notetaker.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    // matters of noteEntity and DTO
    public NoteDTO convertToNoteDTO(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDTO.class);
    }
    public NoteEntity convertTNoteEntity(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, NoteEntity.class);
    }
    public List<NoteDTO> convertToNoteDTO(List<NoteEntity> notes) {
        return modelMapper.map(notes, new TypeToken<List<NoteDTO>>() {}.getType());
    }
    // matters of userEntity and DTO
    public UserDTO convertToUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public UserEntity convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public List<UserDTO> convertToUserDTO(List<UserEntity> users) {
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());
    }
}
