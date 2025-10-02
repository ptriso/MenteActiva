package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
