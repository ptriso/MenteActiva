package pe.edu.upc.menteactiva.dtos.querys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopProfesionalResponseDTO {
    private Long profesionalId;
    private String lastname;
    private String name;
    private Long totalCitas;
}
