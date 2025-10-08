package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String username;
    private Boolean enabled;

    private List<String> authorities;

}
