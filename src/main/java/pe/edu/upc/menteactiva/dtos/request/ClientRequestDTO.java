package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {

    @NotBlank(message = "El campo no puede estar vacio")
    private String name;

    @NotBlank(message = "El campo no puede estar vacio")
    private String lastname;

    @Email
    @NotBlank(message = "El campo no puede estar vacio")
    private String mail;

    @NotBlank(message = "El campo no puede estar vacio")
    private String phone;

    @NotNull(message = "El campo no puede estar vacio")
    private Long userId;
}
