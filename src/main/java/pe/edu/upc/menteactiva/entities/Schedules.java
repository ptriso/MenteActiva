package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time_start", nullable = false)
    private LocalTime time_start;

    @Column(name = "time_ends", nullable = false)
    private LocalTime time_ends;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_id", nullable = false)
    private Profesionals profesional;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "schedule", fetch = FetchType.EAGER)
    private Appointments appointment;

}
