package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="session_summaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session_Summaries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="task",length = 100,nullable = false)
    private String task;

    @Column(name ="progress",length = 100,nullable = false)
    private String progress;

    @Column(name ="conclusion",length = 150,nullable = false)
    private String conclusion;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointments appointment;
}
