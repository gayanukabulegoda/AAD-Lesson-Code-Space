package lk.ijse.notetaker.bo;

import lk.ijse.notetaker.dto.NoteDTO;
import lk.ijse.notetaker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class NoteBoImpl implements NoteBo {
    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setId(AppUtil.createNoteId());
        System.out.println(noteDTO);
        return "NoteDTO Saved Successfully in BO layer";
    }

    @Override
    public boolean updateNote(String id, NoteDTO noteDTO) {
        System.out.println(id);
        System.out.println(noteDTO);
        return true;
    }

    @Override
    public boolean deleteNote(String id) {
        System.out.println(id + " Deleted");
        return true;
    }

    @Override
    public NoteDTO getSelectedNote(String id) {
        System.out.println(id);
        return new NoteDTO(
                "NOTE : 8ecfa465-8f75-4799-b31f-401453ca6b46",
                "This is the first note",
                "Hello Spring : This is a note",
                "L - 02",
                "2021-09-01"
        );
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return null;
    }
}
