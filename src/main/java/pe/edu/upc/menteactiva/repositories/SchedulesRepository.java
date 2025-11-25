package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Schedules;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
    List<Schedules> findByProfesional_Id(Long profesionalId);
}
