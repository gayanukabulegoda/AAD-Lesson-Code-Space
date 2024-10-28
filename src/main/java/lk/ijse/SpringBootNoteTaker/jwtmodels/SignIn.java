package lk.ijse.SpringBootNoteTaker.jwtmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder // in-order to work with builder design pattern
public class SignIn {
    private String email;
    private String password;
}
