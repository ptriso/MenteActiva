package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Schedules;

public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
}
