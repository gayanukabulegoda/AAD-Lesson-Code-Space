package lk.ijse.SpringBootNoteTaker.dto.impl;

import lk.ijse.SpringBootNoteTaker.customObj.NoteResponse;
import lk.ijse.SpringBootNoteTaker.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoteDTO implements SuperDTO, NoteResponse {
    private String id;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
    private String userId;
}
