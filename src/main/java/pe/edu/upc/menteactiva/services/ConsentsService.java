package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.ConsentsRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ConsentsResponseDTO;

import java.util.List;

public interface ConsentsService {
    ConsentsResponseDTO create(ConsentsRequestDTO dto);
    ConsentsResponseDTO update(Long id, ConsentsRequestDTO dto);
    void delete(Long id);
    List<ConsentsResponseDTO> listAll();
}
