package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name",length = 50,nullable = true)
    private String name;

    @Column(name ="lastname",length = 50,nullable = true)
    private String lastname;

    @Column(name ="mail",length = 200,nullable = true)
    private String mail;

    @Column(name ="phone",length = 15,nullable = true)
    private String phone;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "client",orphanRemoval = true, fetch = FetchType.EAGER)
    private Consents consents;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Appointments> appointments = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Video_Progress> video_progress;



}
