package pe.edu.upc.menteactiva.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponseDTO {
    private Long id;
    private String name;
    private String description;
}
