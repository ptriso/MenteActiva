package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StatusRequestDTO {
    @NotBlank(message = "El campo name no puede estar vacío")
    private String name;

    @NotBlank(message = "El campo description no puede estar vacío")
    private String description;
}
