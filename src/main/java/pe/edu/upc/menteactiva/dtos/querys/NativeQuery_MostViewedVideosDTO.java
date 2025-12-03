package pe.edu.upc.menteactiva.dtos.querys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NativeQuery_MostViewedVideosDTO {
    private Long videoId;
    private String videoTitle;
    private Integer totalViews;
    private String authorName;
    private String authorLastname;
    private Long authorId;
}
