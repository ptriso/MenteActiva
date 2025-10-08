package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name",length = 50,nullable = false)
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER)
    private List<User_Authority> user_authorities;


}
