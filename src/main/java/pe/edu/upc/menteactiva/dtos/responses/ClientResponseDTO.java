package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String lastname;
    private String mail;
    private String phone;

    private Long userId;
    private Boolean hasConsent;
    private Integer age;
    private Integer consentAge;
    private String consentDocument;
}
