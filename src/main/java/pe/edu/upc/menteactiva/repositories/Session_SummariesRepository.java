package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Session_Summaries;

public interface Session_SummariesRepository extends JpaRepository<Session_Summaries, Long> {
    boolean existsById(Long id);
}
