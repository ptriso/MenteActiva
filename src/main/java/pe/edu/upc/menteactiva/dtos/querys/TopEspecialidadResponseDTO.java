package pe.edu.upc.menteactiva.dtos.querys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.menteactiva.enums.Specialization;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopEspecialidadResponseDTO {
    private Specialization specialization;
    private Long totalCitas;
}
