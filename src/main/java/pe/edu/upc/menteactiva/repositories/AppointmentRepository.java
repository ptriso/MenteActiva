package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Appointments;

public interface AppointmentRepository extends JpaRepository<Appointments, Long> {
    boolean existsById(Long id);
}
