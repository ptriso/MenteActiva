package pe.edu.upc.menteactiva.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.Duration;

@Getter
@Setter
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
            message = "La URL del video debe ser válida"
    )
    private String url;

    @NotNull(message = "El campo no puede estar vacio")
    private Integer duration;

    @NotNull(message = "El campo no puede estar vacio")
    private Long professionalId;
}
