package pe.edu.upc.menteactiva.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentsResponseDTO {
    private Long id;
    private Integer age;
    private String docConsent;
    private Long clientId;
}
