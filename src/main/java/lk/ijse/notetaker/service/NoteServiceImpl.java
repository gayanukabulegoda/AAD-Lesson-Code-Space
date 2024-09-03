package lk.ijse.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.notetaker.entity.NoteEntity;
import lk.ijse.notetaker.repository.NoteRepository;
import lk.ijse.notetaker.dto.NoteDTO;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final Mapping mapping;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, Mapping mapping) {
        this.noteRepository = noteRepository;
        this.mapping = mapping;
    }

    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setId(AppUtil.createNoteId());
        noteRepository.save(mapping.convertTNoteEntity(noteDTO));
        return "NoteDTO Saved Successfully";
    }

    @Override
    public boolean updateNote(String id, NoteDTO incomingNoteDTO) {
        Optional<NoteEntity> tmpNoteEntity = noteRepository.findById(id);
        if (!tmpNoteEntity.isPresent()) {
            return false;
        } else {
            tmpNoteEntity.get().setNoteDesc(incomingNoteDTO.getNoteDesc());
            tmpNoteEntity.get().setNoteTitle(incomingNoteDTO.getNoteTitle());
            tmpNoteEntity.get().setPriorityLevel(incomingNoteDTO.getPriorityLevel());
            tmpNoteEntity.get().setCreateDate(incomingNoteDTO.getCreateDate());
            return true;
        }
    }

    @Override
    public boolean deleteNote(String id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public NoteDTO getSelectedNote(String id) {
        return mapping.convertToNoteDTO(noteRepository.getReferenceById(id));
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return mapping.convertToNoteDTO(noteRepository.findAll());
    }
}
