package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;
import pe.edu.upc.menteactiva.enums.StatusAp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponseDTO {
    private Long id;
    private StatusAp statusap;
    private String description;
}
