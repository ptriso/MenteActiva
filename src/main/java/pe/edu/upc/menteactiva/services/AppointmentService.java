package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.querys.TopEspecialidadResponseDTO;
import pe.edu.upc.menteactiva.dtos.request.AppointmentRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentClientResponseDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    AppointmentResponseDTO create(AppointmentRequestDTO dto);
    AppointmentResponseDTO update(Long id, AppointmentRequestDTO dto);
    void delete(Long id);
    List<AppointmentResponseDTO> listAll();
    Optional<AppointmentResponseDTO> proximaCitaDeCliente(Long clientId);
    List<pe.edu.upc.menteactiva.dtos.querys.TopProfesionalResponseDTO> topProfesionalesMasCitas(int top);
    List<TopEspecialidadResponseDTO> topEspecialidades(int top);
    List<pe.edu.upc.menteactiva.dtos.querys.TopClientesResponseDTO> topClientesMasCitasTodas(int top);
    List<AppointmentClientResponseDTO> listByClientId(Long clientId);
    void cancel(Long id);
}
