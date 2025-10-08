package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponseDTO {
    private Long id;
    private String name;
    private String description;
}
