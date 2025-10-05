package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Chats;

public interface ChatRepository extends JpaRepository<Chats, Long> {
    boolean existsById(Long id);
}
