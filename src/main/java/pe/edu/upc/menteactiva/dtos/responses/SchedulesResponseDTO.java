package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulesResponseDTO {

    private Long id;
    private LocalDate date;
    private LocalTime time_start;
    private LocalTime time_ends;

    private Long profesionalId;
}
