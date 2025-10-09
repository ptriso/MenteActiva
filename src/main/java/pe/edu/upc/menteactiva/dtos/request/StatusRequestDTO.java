package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import pe.edu.upc.menteactiva.enums.StatusAp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StatusRequestDTO {
    @NotBlank(message = "El campo no puede estar vacio")
    private StatusAp statusap;

    @NotBlank(message = "El campo description no puede estar vacío")
    private String description;
}
