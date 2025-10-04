package pe.edu.upc.menteactiva.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequestDTO {
    @NotBlank(message = "El campo no puede estar vacio")
    private String title;

    @NotBlank(message = "El campo no puede estar vacio")
    private String description;

    @NotBlank(message = "El campo no puede estar vacio")
    @Pattern(
            regexp = "^(https?|ftp)://.*$",
            message = "La URL de la foto debe ser válida"
    )
    private String url;

    @NotNull(message = "El campo no puede estar vacio")
    private Duration duration;

    @NotNull(message = "El campo no puede estar vacio")
    private Long professionalId;
}
