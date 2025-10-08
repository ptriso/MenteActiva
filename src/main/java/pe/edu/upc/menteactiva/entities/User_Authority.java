package pe.edu.upc.menteactiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users_authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User_Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="authority_id")
    private Authority authority;

    
}
