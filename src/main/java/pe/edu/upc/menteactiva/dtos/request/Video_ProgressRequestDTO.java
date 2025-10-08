package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Video_ProgressRequestDTO {
    @NotNull(message = "El campo no puede estar vacio")
    private Long clientId;

    @NotNull(message = "El campo no puede estar vacio")
    private Long videoId;

    @NotNull(message = "El campo no puede estar vacio")
    private Long percentage;

    @NotNull(message = "El campo no puede estar vacio")
    private Integer current_time;

    @NotNull(message = "El campo no puede estar vacio")
    private Boolean completed;

    @NotNull(message = "El campo no puede estar vacio")
    private Integer views_count;
}
