package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pe.edu.upc.menteactiva.enums.Specialization;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="profesionals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesionals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name",length = 50,nullable = false)
    private String name;

    @Column(name ="lastname",length = 50,nullable = false)
    private String lastname;

    @Column(name ="specialization", nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialization  specialization;

    @Column(name ="mail",length = 200,nullable = false)
    private String mail;

    @Column(name ="phone",length = 15,nullable = false)
    private String phone;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "profesional", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Videos> videos = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "profesional", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Schedules> schedules = new ArrayList<>();
}
