package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session_SummariesResponseDTO {
    private Long id;
    private String task;
    private String progress;
    private String conclusion;
    private Long appointmentId;
}
