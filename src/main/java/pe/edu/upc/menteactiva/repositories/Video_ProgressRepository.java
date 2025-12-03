package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.menteactiva.entities.Clients;
import pe.edu.upc.menteactiva.entities.Video_Progress;
import pe.edu.upc.menteactiva.entities.Videos;

import java.util.List;
import java.util.Optional;

public interface Video_ProgressRepository extends JpaRepository<Video_Progress, Long> {
    Optional<Video_Progress> findByClientAndVideo(Clients client, Videos video);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Video_Progress vp WHERE vp.id = :id")
    int hardDeleteById(@Param("id") Long id);

    List<Video_Progress> findByClientId(Long clientId);
}
