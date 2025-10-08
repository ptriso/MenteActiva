package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session_SummariesRequestDTO {
    @NotBlank(message = "El campo task no puede estar vacío")
    private String task;

    @NotBlank(message = "El campo progress no puede estar vacío")
    private String progress;

    @NotBlank(message = "El campo conclusion no puede estar vacío")
    private String conclusion;

    @NotNull(message = "El campo appointmentId no puede estar vacío")
    private Long appointmentId;
}
