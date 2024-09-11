package lk.ijse.notetaker.service;

import lk.ijse.notetaker.dto.NoteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {
    String saveNote(NoteDTO noteDTO);
    boolean updateNote(String noteId,NoteDTO noteDTO);
    boolean deleteNote(String noteId);
    NoteDTO getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
