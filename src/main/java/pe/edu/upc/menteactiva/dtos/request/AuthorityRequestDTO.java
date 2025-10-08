package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityRequestDTO {
     @NotBlank(message = "El campo no puede estar vacio")
     private String name;
}
