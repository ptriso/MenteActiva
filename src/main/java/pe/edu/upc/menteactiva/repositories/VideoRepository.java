package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Videos;

import java.util.List;

public interface VideoRepository extends JpaRepository<Videos, Long> {
    boolean existsById(Long id);
    //Top 5 más largos de un profesional
    List<Videos> findTop5ByProfesional_IdOrderByDurationDesc(Long professionalId);

    //¿Cuántos videos tiene un profesional? (KPI simple)
    long countByProfesional_Id(Long professionalId);

}
