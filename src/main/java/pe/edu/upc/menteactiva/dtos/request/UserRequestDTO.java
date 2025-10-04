package pe.edu.upc.menteactiva.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "El campo no puede estar vacio")
    private String username;

    @NotBlank(message = "El campo no puede estar vacio")
    private String password;

    @NotNull(message = "El campo no puede estar vacio")
    private Boolean enabled;

    @NotNull(message = "El campo no puede estar vacio")
    private Long authorityId;
}
