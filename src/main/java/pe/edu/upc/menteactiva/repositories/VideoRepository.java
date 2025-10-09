package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.menteactiva.entities.Videos;

import java.util.List;

public interface VideoRepository extends JpaRepository<Videos, Long> {
    boolean existsById(Long id);

    @Query(
            value = """
    SELECT 
        v.title AS titulo,
        CONCAT(p.name, ' ', p.lastname) AS autor,
        SUM(vp.views_count) AS total_vistas
        FROM videos v
        JOIN video_progress vp ON vp.video_id = v.id
        JOIN profesionals p ON v.profesional_id = p.id
        GROUP BY v.title, p.name, p.lastname
        ORDER BY total_vistas DESC
      """,
            nativeQuery = true)
    List<Object[]> getMostViewedVideos();
    //Top 5 más largos de un profesional
    List<Videos> findTop5ByProfesional_IdOrderByDurationDesc(Long professionalId);

    //¿Cuántos videos tiene un profesional? (KPI simple)
    long countByProfesional_Id(Long professionalId);

    // Búsqueda por texto en título (search bar)
    List<Videos> findByTitleContainingIgnoreCase(String titlePart);
}
