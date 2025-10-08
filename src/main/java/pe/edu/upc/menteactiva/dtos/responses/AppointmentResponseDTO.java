package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDTO {
    private Long id;
    private Long clientId;
    private Long statusId;
    private Long scheduleId;
}
