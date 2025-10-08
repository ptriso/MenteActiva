package pe.edu.upc.menteactiva.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "El campo no puede estar vacio")
    private String username;

    @NotBlank(message = "El campo no puede estar vacio")
    private String password;

    @NotNull(message = "El campo no puede estar vacio")
    private Boolean enabled;

}
