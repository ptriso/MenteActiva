package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;
import pe.edu.upc.menteactiva.enums.Specialization;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalResponseDTO {
    private Long id;
    private String name;
    private String lastname;
    private Specialization specialization;
    private String mail;
    private String phone;

    private Long userId;
}
