package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.Video_ProgressRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.Video_ProgressResponseDTO;

import java.util.List;

public interface Video_ProgressService {

    Video_ProgressResponseDTO create(Video_ProgressRequestDTO dto);
    Video_ProgressResponseDTO update(Long id, Video_ProgressRequestDTO dto);

    void delete(Long id);
    List<Video_ProgressResponseDTO> listAll();
    List<Video_ProgressResponseDTO> listByClient(Long clientId);

}
