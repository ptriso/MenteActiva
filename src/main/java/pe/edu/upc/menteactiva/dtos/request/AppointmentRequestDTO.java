package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDTO {

    @NotNull(message = "El campo clientId no puede estar vacío")
    private Long clientId;

    @NotNull(message = "El campo statusId no puede estar vacío")
    private Long statusId;

    @NotNull(message = "El campo scheduleId no puede estar vacío")
    private Long scheduleId;
}
