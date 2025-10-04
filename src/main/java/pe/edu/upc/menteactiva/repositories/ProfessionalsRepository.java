package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Profesionals;

public interface ProfessionalsRepository extends JpaRepository<Profesionals, Long> {
    boolean existsById(Long id);
}
