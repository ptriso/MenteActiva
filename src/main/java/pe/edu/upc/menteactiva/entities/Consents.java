package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="consents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="age",length = 20,nullable = false)
    private Integer age;

    @Column(name ="doc_consent",length = 50,nullable = false)
    private String doc_consent;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "client_id")
    private Clients client;

}
