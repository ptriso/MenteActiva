package pe.edu.upc.menteactiva.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="username",length = 50,nullable = false)
    private String username;

    @Column(name ="password",length = 50,nullable = false)
    private String password;

    @Column(name ="enabled", nullable = false)
    private boolean enabled;

}
