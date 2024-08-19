package lk.ijse.notetaker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoteDTO implements Serializable {
    private String id;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
}
