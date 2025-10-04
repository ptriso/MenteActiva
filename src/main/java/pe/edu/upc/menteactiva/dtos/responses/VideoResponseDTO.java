package pe.edu.upc.menteactiva.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String url;
    private Duration duration;
    private Long professionalId;
}
