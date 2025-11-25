package pe.edu.upc.menteactiva.dtos.responses;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.menteactiva.enums.StatusAp;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AppointmentClientResponseDTO {

    private Long id;
    private String professionalName;
    private String professionalLastname;
    private String date;
    private String timeStart;
    private String timeEnds;
    private String status;

    // --- ¡ESTE ES EL CONSTRUCTOR CORREGIDO! ---
    public AppointmentClientResponseDTO(
            Long id,
            String professionalName,
            String professionalLastname,
            String date,
            String timeStart,
            String timeEnds,
            StatusAp statusEnum) { // <-- 2. ACEPTA EL ENUM

        this.id = id;
        this.professionalName = professionalName;
        this.professionalLastname = professionalLastname;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnds = timeEnds;
        this.status = statusEnum.name(); // <-- 3. CONVIERTE EL ENUM A STRING AQUÍ
    }
}