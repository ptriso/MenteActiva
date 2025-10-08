package pe.edu.upc.menteactiva.dtos.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulesRequestDTO {
    @NotNull(message = "El campo no puede estar vacio")
    private LocalDate date;

    @NotNull(message = "El campo no puede estar vacio")
    private LocalTime time_start;

    @NotNull(message = "El campo no puede estar vacio")
    private LocalTime time_ends;

    @NotNull(message = "El campo no puede estar vacio")
    private Long profesionalId;
}
