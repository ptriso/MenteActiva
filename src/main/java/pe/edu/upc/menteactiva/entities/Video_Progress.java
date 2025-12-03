package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="video_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video_Progress {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Clients client;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_id")
    private Videos video;

    @Column(name = "percentage", nullable = false)
    private Long percentage;

    @Column(name = "current_times", nullable = false)
    private Integer current_time;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @Column(name = "views_count", nullable = false)
    private Integer views_count;

}
