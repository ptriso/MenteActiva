package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User_AuthorityRequestDTO {

    @NotNull(message = "El campo no puede estar vacio")
    private Long userId;

    @NotNull(message = "El campo no puede estar vacio")
    private Long authorityId;

}
