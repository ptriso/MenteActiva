package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Status;

public interface StatusRepository extends JpaRepository<Status,Long> {
    boolean existsById(Long id);
}
