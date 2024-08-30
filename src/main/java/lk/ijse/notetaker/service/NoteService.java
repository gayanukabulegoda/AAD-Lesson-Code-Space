package lk.ijse.notetaker.service;

import lk.ijse.notetaker.dto.NoteDTO;

import java.util.List;

public sealed interface NoteService permits NoteServiceImpl {
    String saveNote(NoteDTO noteDTO);
    void updateNote(String id, NoteDTO noteDTO);
    void deleteNote(String id);
    NoteDTO getSelectedNote(String id);
    List<NoteDTO> getAllNotes();
}
