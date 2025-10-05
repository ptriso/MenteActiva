package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequestDTO {
    @NotNull(message = "El campo appointmentId no puede estar vacío")
    private Long appointmentId;
}
