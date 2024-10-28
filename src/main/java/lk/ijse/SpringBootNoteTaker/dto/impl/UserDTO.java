package lk.ijse.SpringBootNoteTaker.dto.impl;

import lk.ijse.SpringBootNoteTaker.customObj.UserResponse;
import lk.ijse.SpringBootNoteTaker.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePic;
    private String role;
    private List<NoteDTO> notes = new ArrayList<>();
}
