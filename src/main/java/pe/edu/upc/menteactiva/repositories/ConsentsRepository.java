package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Consents;

public interface ConsentsRepository extends JpaRepository<Consents, Long> {

}
