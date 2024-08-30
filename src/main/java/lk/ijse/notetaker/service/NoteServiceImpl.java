package lk.ijse.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.notetaker.repository.NoteRepository;
import lk.ijse.notetaker.dto.NoteDTO;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        noteRepository.save(mapping.convertToEntity(noteDTO));
        return "NoteDTO Saved Successfully";
    }

    @Override
    public void updateNote(String id, NoteDTO incomingNoteDTO) {
    }

    @Override
    public void deleteNote(String id) {
    }

    @Override
    public NoteDTO getSelectedNote(String id) {
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return null;
    }
}
