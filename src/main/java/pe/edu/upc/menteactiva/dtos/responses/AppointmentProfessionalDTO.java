package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentProfessionalDTO {

    private Long id;

    private String clientName;
    private String clientLastname;

    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;

    private String status;
}

