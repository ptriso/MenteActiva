package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.StatusRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.StatusResponseDTO;

import java.util.List;

public interface StatusService {
    StatusResponseDTO create(StatusRequestDTO dto);
    StatusResponseDTO update(Long id, StatusRequestDTO dto);
    void delete(Long id);
    List<StatusResponseDTO> listAll();
}
