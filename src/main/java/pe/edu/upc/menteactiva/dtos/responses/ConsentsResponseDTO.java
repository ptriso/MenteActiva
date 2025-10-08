package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsentsResponseDTO {
    private Long id;
    private Integer age;
    private String docConsent;
    private Long clientId;
}
