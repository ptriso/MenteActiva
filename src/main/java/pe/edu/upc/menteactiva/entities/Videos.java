package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Entity
@Table(name="videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "description", length = 2000, nullable = false)
    private String description;

    @Column(name = "url", length = 500, nullable = false)
    private String url;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_id", nullable = false)
    private Profesionals profesional;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "video", fetch = FetchType.EAGER)
    private Video_Progress video_progress;

}
