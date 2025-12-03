package pe.edu.upc.menteactiva.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOUser {
    private Long id;
    private String username;
    private String password;
    private String authorities;
}
