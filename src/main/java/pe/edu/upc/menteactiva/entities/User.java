package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="username",length = 50,nullable = false)
    private String username;

    @Column(name ="password",length = 200,nullable = false)
    private String password;

    @Column(name ="enabled", nullable = false)
    private boolean enabled;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<User_Authority> user_authority;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Clients clients;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Profesionals profesionals;


}
