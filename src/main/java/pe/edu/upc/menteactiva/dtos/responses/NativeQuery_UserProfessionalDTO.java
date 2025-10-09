package pe.edu.upc.menteactiva.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NativeQuery_UserProfessionalDTO {
    private Long idUsuario;
    private String username;
    private String nombre;
    private String apellido;
    private String especializacion;
}
