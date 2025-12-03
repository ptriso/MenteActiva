package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mensaje", nullable = false, length = 1000)
    private String mensaje;

    @Column(name = "sender_type", nullable = false, length = 50)
    private String sender_type;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointments appointment;

}
