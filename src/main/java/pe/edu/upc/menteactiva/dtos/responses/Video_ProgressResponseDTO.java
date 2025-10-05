package pe.edu.upc.menteactiva.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video_ProgressResponseDTO {

    private Long id;
    private Long clientId;
    private Long videoId;
    private Long percentage;
    private Integer current_time;
    private Boolean completed;
    private Integer views_count;
}
