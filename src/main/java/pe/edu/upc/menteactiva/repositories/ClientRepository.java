package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Clients;

public interface ClientRepository extends JpaRepository<Clients, Long> {
    boolean existsById(Long id);
}
