package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.SchedulesRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.SchedulesResponseDTO;

import java.util.List;

public interface SchedulesService {

    SchedulesResponseDTO create(SchedulesRequestDTO dto);
    SchedulesResponseDTO update(Long id, SchedulesRequestDTO dto);
    void delete(Long id);
    List<SchedulesResponseDTO> listAll();
}
