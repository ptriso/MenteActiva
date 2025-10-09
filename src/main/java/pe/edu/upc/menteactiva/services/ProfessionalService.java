package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.ProfessionalRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ProfessionalResponseDTO;
import pe.edu.upc.menteactiva.enums.Specialization;

import java.util.List;

public interface ProfessionalService {

    ProfessionalResponseDTO create(ProfessionalRequestDTO dto);
    ProfessionalResponseDTO update(Long id, ProfessionalRequestDTO dto);
    void delete(Long id);
    List<ProfessionalResponseDTO> listAll();
}
