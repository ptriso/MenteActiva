package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Duration;

@Entity
@Table(name="videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 150, nullable = false)
    private String description;

    @Column(name = "url", length = 50, nullable = false)
    private String url;

    @Column(name = "duration", nullable = false)
    private Duration duration;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "profesional_id")
    private Profesionals profesional;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "video", fetch = FetchType.EAGER)
    private Video_Progress video_progress;

}
