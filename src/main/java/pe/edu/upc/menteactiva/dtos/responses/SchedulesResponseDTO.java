package pe.edu.upc.menteactiva.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulesResponseDTO {

    private Long id;
    private LocalDate date;
    private LocalTime time_start;
    private LocalTime time_ends;

    private Long profesionalId;
}
