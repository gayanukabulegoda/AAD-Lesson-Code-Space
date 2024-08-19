package lk.ijse.notetaker.bo;

import lk.ijse.notetaker.dto.NoteDTO;

import java.util.List;

public sealed interface NoteBo permits NoteBoImpl {
    String saveNote(NoteDTO noteDTO);
    boolean updateNote(String id, NoteDTO noteDTO);
    boolean deleteNote(String id);
    NoteDTO getSelectedNote(String id);
    List<NoteDTO> getAllNotes();
}
