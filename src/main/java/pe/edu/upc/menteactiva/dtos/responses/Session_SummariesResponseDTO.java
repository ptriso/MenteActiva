package pe.edu.upc.menteactiva.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session_SummariesResponseDTO {
    private Long id;
    private String task;
    private String progress;
    private String conclusion;
    private Long appointmentId;
}
