package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.Session_SummariesRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.Session_SummariesResponseDTO;
import pe.edu.upc.menteactiva.entities.Appointments;
import pe.edu.upc.menteactiva.entities.Session_Summaries;
import pe.edu.upc.menteactiva.repositories.AppointmentRepository;
import pe.edu.upc.menteactiva.repositories.Session_SummariesRepository;
import pe.edu.upc.menteactiva.services.Session_SummariesService;

import java.util.List;

@Service
public class Session_SummariesServiceImplements implements Session_SummariesService {
    @Autowired
    private Session_SummariesRepository sessionSummariesRepository;

    @Autowired
    private AppointmentRepository appointmentsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Session_SummariesResponseDTO create(Session_SummariesRequestDTO dto) {
        Appointments appointment = appointmentsRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        Session_Summaries summary = new Session_Summaries();
        summary.setAppointment(appointment);
        summary.setTask(dto.getTask());
        summary.setProgress(dto.getProgress());
        summary.setConclusion(dto.getConclusion());

        return modelMapper.map(sessionSummariesRepository.save(summary), Session_SummariesResponseDTO.class);
    }
    @Override
    public Session_SummariesResponseDTO update(Long id, Session_SummariesRequestDTO dto) {
        Session_Summaries summary = sessionSummariesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resumen no encontrado"));

        Appointments appointment = appointmentsRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        summary.setAppointment(appointment);
        summary.setTask(dto.getTask());
        summary.setProgress(dto.getProgress());
        summary.setConclusion(dto.getConclusion());

        return modelMapper.map(sessionSummariesRepository.save(summary), Session_SummariesResponseDTO.class);
    }
    @Override
    public void delete(Long id) {
        if (!sessionSummariesRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resumen no encontrado");
        }
        sessionSummariesRepository.deleteById(id);
    }

    @Override
    public List<Session_SummariesResponseDTO> listAll() {
        return sessionSummariesRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, Session_SummariesResponseDTO.class))
                .toList();
    }
}
