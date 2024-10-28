package lk.ijse.SpringBootNoteTaker.jwtmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JWTAuthResponse {
    private String token;
}
