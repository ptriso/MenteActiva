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
        v.id AS videoId,
        v.title AS videoTitle,
        SUM(vp.views_count) AS totalViews,
        p.name AS authorName,
        p.lastname AS authorLastname,
        p.id AS authorId
    FROM videos v
    JOIN video_progress vp ON v.id = vp.video_id
    JOIN profesionals p ON v.profesional_id = p.id
    GROUP BY v.id, v.title, p.name, p.lastname, p.id
    ORDER BY totalViews DESC
    """,
            nativeQuery = true
    )
    List<Object[]> getMostViewedVideos();

    List<Videos> findTop5ByProfesional_IdOrderByDurationDesc(Long professionalId);

    long countByProfesional_Id(Long professionalId);

    List<Videos> findByTitleContainingIgnoreCase(String titlePart);

    List<Videos> findByProfesionalId(Long professionalId);
}
