package lk.ijse.notetaker.controller;

import lk.ijse.notetaker.bo.NoteBo;
import lk.ijse.notetaker.dto.NoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {
    @Autowired
    private final NoteBo noteBo;

    //ToDo: CRUD of the Note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDTO noteDTO) {
        var isSaved = noteBo.saveNote(noteDTO);
        return ResponseEntity.ok(isSaved);
    }

    @GetMapping(value ="allNotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes() {
        return noteBo.getAllNotes();
    }

    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO getNote(@PathVariable("noteId") String noteId) {
        return noteBo.getSelectedNote(noteId);
    }

    @PatchMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO noteDTO) {
        boolean isUpdated = noteBo.updateNote(noteId, noteDTO);
    }

    @DeleteMapping(value = "/{noteId}")
    public void deleteNote(@PathVariable ("noteId") String noteId) {
        boolean isDeleted = noteBo.deleteNote(noteId);
    }
}
