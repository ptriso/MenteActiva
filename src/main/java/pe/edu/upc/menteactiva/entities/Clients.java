package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name",length = 50,nullable = false)
    private String name;

    @Column(name ="lastname",length = 50,nullable = false)
    private String lastname;

    @Email
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
    @OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
    private Consents consents;


}
