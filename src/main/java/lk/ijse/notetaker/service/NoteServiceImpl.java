package lk.ijse.notetaker.service;

import lk.ijse.notetaker.dto.NoteDTO;
import lk.ijse.notetaker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public final class NoteServiceImpl implements NoteService {
    List<NoteDTO> noteList = new ArrayList<>();
    public NoteServiceImpl() {
        noteList.add(new NoteDTO("NOTE : 8ecfa465-8f75-4799-b31f-401453ca6b46", "This is the first note", "Hello Spring : This is note 01", "L - 01", "2021-09-01"));
        noteList.add(new NoteDTO("NOTE : 8ecfa465-8f75-4799-b31f-401453ca6b47", "This is the second note", "Hello Spring : This is note 02", "L - 02", "2021-09-02"));
        noteList.add(new NoteDTO("NOTE : 8ecfa465-8f75-4799-b31f-401453ca6b48", "This is the third note", "Hello Spring : This is note 03", "L - 03", "2021-09-03"));
        noteList.add(new NoteDTO("NOTE : 8ecfa465-8f75-4799-b31f-401453ca6b49", "This is the fourth note", "Hello Spring : This is note 04", "L - 04", "2021-09-04"));
    }

    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setId(AppUtil.createNoteId());
        noteList.add(noteDTO);
        return "NoteDTO Saved Successfully";
    }

    @Override
    public void updateNote(String id, NoteDTO incomingNoteDTO) {
/*        for (NoteDTO updateNode : noteList) {
            if (updateNode.getId().equals(id)) {
                updateNode.setNoteDesc(incomingNoteDTO.getNoteDesc());
                updateNode.setNoteTitle(incomingNoteDTO.getNoteTitle());
                updateNode.setPriorityLevel(incomingNoteDTO.getPriorityLevel());
                updateNode.setCreateDate(incomingNoteDTO.getCreateDate());
            }
        }*/

        // ----- Using List Iterator -----

        ListIterator<NoteDTO> updatedList = noteList.listIterator();
        while (updatedList.hasNext()) {
            NoteDTO noteDTO = updatedList.next();
            if (noteDTO.getId().equals(id)) {
                incomingNoteDTO.setId(noteDTO.getId());
                updatedList.set(incomingNoteDTO);
                break;
            }
        }
    }

    @Override
    public void deleteNote(String id) {
        /*for (NoteDTO updateNode : noteList) {
            if (updateNode.getId().equals(id)) {
                noteList.remove(updateNode);
                return true;
            }
        }
        return false;*/

        // ----- Using List Iterator -----

        ListIterator<NoteDTO> tmpList = noteList.listIterator();
        while (tmpList.hasNext()) {
            NoteDTO noteDTO = tmpList.next();
            if (noteDTO.getId().equals(id)) {
                tmpList.remove();
            }
        }
    }

    @Override
    public NoteDTO getSelectedNote(String id) {
        for (NoteDTO noteDTO : getAllNotes()) {
            if (noteDTO.getId().equals(id)) {
                return noteDTO;
            }
        }
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteList;
    }
}
