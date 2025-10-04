package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Videos;

public interface VideoRepository extends JpaRepository<Videos, Long> {
    boolean existsById(Long id);
}
