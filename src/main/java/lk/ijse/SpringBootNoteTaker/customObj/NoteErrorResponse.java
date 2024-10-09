package lk.ijse.SpringBootNoteTaker.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoteErrorResponse implements NoteResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
