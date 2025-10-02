package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="video_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video_Progress {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "client_id")
    private Clients clients;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "video_id")
    private Videos videos;

    @Column(name = "percentage", nullable = false)
    private Long percentage;

    @Column(name = "current_time", nullable = false)
    private Integer current_time;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @Column(name = "views_count", nullable = false)
    private Integer views_count;


}
