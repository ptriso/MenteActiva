package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentsRequestDTO {

    @NotNull(message = "El campo no puede estar vacio")
    private Integer age;

    @NotBlank(message = "El campo no puede estar vacio")
    @Pattern(
            regexp = "^(https?|ftp)://.*$",
            message = "La URL de la foto debe ser válida"
    )
    private String doc_consent;

    @NotNull(message = "El campo no puede estar vacio")
    private Long clientId;
}
