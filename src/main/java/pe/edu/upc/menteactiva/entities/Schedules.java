package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime time_start;

    @Column(name = "time_end", nullable = false)
    private LocalDateTime time_end;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "schedules", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Appointments> appointments = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "profesional_id")
    private Profesionals profesional;
}
