package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.VideoRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.NativeQuery_MostViewedVideosDTO;
import pe.edu.upc.menteactiva.dtos.responses.VideoResponseDTO;

import java.util.List;

public interface VideoService {

    VideoResponseDTO create (VideoRequestDTO dto);
    VideoResponseDTO update (Long id, VideoRequestDTO dto);
    void delete(Long id);
    List<VideoResponseDTO> listAll();

    List<NativeQuery_MostViewedVideosDTO> getMostViewedVideos();
}
