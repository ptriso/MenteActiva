package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String url;
    private Integer duration;
    private Long professionalId;
}
