package lk.ijse.notetaker.service;

import lk.ijse.notetaker.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    String saveNote(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    void deleteNote(String noteId);
    NoteDTO getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
