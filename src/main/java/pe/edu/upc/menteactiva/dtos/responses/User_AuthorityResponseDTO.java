package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User_AuthorityResponseDTO {

    private Long id;
    private Long userId;
    private Long authorityId;

}
