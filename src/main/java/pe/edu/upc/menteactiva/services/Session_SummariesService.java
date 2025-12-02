package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.Session_SummariesRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.Session_SummariesResponseDTO;

import java.util.List;

public interface Session_SummariesService {
    Session_SummariesResponseDTO create(Session_SummariesRequestDTO dto);
    Session_SummariesResponseDTO update(Long id, Session_SummariesRequestDTO dto);
    void delete(Long id);
    List<Session_SummariesResponseDTO> listAll();
    Session_SummariesResponseDTO findByAppointmentId(Long appointmentId);
    Session_SummariesResponseDTO saveOrUpdateByAppointment(
            Long appointmentId,
            Session_SummariesRequestDTO dto
    );
}
