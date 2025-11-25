package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Clients client;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedules_id")
    private Schedules schedule;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "appointment", fetch = FetchType.EAGER)
    private Chats chats;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "appointment", fetch = FetchType.EAGER)
    private Session_Summaries session_summaries;
}
