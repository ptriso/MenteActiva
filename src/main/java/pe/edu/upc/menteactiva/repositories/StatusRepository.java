package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Status;
import pe.edu.upc.menteactiva.enums.StatusAp;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Long> {
    boolean existsById(Long id);
    Optional<Status> findByStatusap(StatusAp statusap);

}
