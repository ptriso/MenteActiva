package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    boolean existsByName(String name);

    Authority findByName(String name);
}
