package lk.ijse.notetaker.util;

import lk.ijse.notetaker.dto.NoteDTO;
import lk.ijse.notetaker.entity.NoteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    // matters of noteEntity and DTO
    public NoteDTO convertToDTO(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDTO.class);
    }
    public NoteEntity convertToEntity(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, NoteEntity.class);
    }
    public List<NoteDTO> convertToDTO(List<NoteEntity> notes) {
        return modelMapper.map(notes, List.class);
    }
}
