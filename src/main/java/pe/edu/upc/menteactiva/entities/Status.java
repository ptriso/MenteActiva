package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 50, nullable = false)
    private String name;

    @Column(name = "description",length = 150, nullable = false)
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    private List<Appointments> appointments = new ArrayList<>();


}
