package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseDTO {
    private Long id;
    private Long appointmentId;
    private String mensaje;
    private String sender_type;
    private LocalDateTime timestamp;
}
