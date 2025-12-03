package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Chats;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chats, Long> {
    boolean existsById(Long id);

    List<Chats> findByAppointment_Client_IdOrderByIdDesc(Long clientId);

    List<Chats> findByAppointment_Id(Long appointmentId);
}
