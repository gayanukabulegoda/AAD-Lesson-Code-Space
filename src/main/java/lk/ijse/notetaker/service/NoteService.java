package lk.ijse.notetaker.service;

import lk.ijse.notetaker.customObj.NoteResponse;
import lk.ijse.notetaker.dto.impl.NoteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {
    void saveNote(String noteId, NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    void deleteNote(String noteId);
    NoteResponse getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
