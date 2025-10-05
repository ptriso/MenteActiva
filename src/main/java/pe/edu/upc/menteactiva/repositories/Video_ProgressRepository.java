package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.Clients;
import pe.edu.upc.menteactiva.entities.Video_Progress;
import pe.edu.upc.menteactiva.entities.Videos;

import java.util.Optional;

public interface Video_ProgressRepository extends JpaRepository<Video_Progress, Long> {
    Optional<Video_Progress> findByClientAndVideo(Clients client, Videos video);
}
