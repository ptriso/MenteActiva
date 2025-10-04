package pe.edu.upc.menteactiva.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_AuthorityResponseDTO {

    private Long id;
    private Long userId;
    private Long authorityId;

    //private String username;
    //private String authorityName;
}
