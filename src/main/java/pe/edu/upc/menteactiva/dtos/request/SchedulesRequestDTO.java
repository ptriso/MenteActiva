package pe.edu.upc.menteactiva.dtos.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
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
