package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.VideoRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.VideoResponseDTO;
import pe.edu.upc.menteactiva.entities.Videos;

import java.util.List;

public interface VideoService {

    VideoResponseDTO create (VideoRequestDTO dto);
    VideoResponseDTO update (Long id, VideoRequestDTO dto);
    void delete(Long id);
    List<VideoResponseDTO> listAll();

    List<Videos> top5MasLargosPorProfesional(Long professionalId);
    long contarPorProfesional(Long professionalId);
}
