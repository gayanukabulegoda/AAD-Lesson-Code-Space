package lk.ijse.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.notetaker.customObj.NoteErrorResponse;
import lk.ijse.notetaker.customObj.NoteResponse;
import lk.ijse.notetaker.entity.NoteEntity;
import lk.ijse.notetaker.exception.NoteNotFoundException;
import lk.ijse.notetaker.repository.NoteRepository;
import lk.ijse.notetaker.dto.impl.NoteDTO;
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
    public void updateNote(String id, NoteDTO incomingNoteDTO) {
        Optional<NoteEntity> tmpNoteEntity = noteRepository.findById(id);
        if (!tmpNoteEntity.isPresent()) {
            throw new NoteNotFoundException("Note Not Found");
        } else {
            tmpNoteEntity.get().setNoteDesc(incomingNoteDTO.getNoteDesc());
            tmpNoteEntity.get().setNoteTitle(incomingNoteDTO.getNoteTitle());
            tmpNoteEntity.get().setPriorityLevel(incomingNoteDTO.getPriorityLevel());
            tmpNoteEntity.get().setCreateDate(incomingNoteDTO.getCreateDate());
        }
    }

    @Override
    public void deleteNote(String id) {
        Optional<NoteEntity> selectedNote = noteRepository.findById(id);
        if (!selectedNote.isPresent()) {
            throw new NoteNotFoundException("Note Not Found");
        } else noteRepository.deleteById(id);
    }

    @Override
    public NoteResponse getSelectedNote(String id) {
        return (noteRepository.existsById(id))
                ? mapping.convertToNoteDTO(noteRepository.getReferenceById(id))
                : new NoteErrorResponse(0, "Note Not Found");
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return mapping.convertToNoteDTO(noteRepository.findAll());
    }
}
