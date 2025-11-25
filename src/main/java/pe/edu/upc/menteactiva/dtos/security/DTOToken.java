package pe.edu.upc.menteactiva.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOToken {
    private String jwtToken;
    private Long id;
    private String username;
    private String authorities; // Formato: "ROLE_USER;ROLE_ADMIN"
    private Long profileId;
}
