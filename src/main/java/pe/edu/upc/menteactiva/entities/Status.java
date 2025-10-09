package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pe.edu.upc.menteactiva.enums.StatusAp;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_ap", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAp statusap;

    @Column(name = "description",length = 150, nullable = false)
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    private List<Appointments> appointments = new ArrayList<>();


}
