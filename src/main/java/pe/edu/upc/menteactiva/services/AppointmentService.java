package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.AppointmentRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDTO create(AppointmentRequestDTO dto);
    AppointmentResponseDTO update(Long id, AppointmentRequestDTO dto);
    void delete(Long id);
    List<AppointmentResponseDTO> listAll();
}
