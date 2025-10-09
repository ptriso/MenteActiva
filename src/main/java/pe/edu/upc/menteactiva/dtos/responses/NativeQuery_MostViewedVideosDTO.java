package pe.edu.upc.menteactiva.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NativeQuery_MostViewedVideosDTO {
    private String titulo;
    private String autor;
    private Long totalVistas;
}
