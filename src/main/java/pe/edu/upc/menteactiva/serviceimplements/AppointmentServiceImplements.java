package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.AppointmentRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentResponseDTO;
import pe.edu.upc.menteactiva.entities.Appointments;
import pe.edu.upc.menteactiva.entities.Clients;
import pe.edu.upc.menteactiva.entities.Schedules;
import pe.edu.upc.menteactiva.entities.Status;
import pe.edu.upc.menteactiva.repositories.AppointmentRepository;
import pe.edu.upc.menteactiva.repositories.ClientRepository;
import pe.edu.upc.menteactiva.repositories.SchedulesRepository;
import pe.edu.upc.menteactiva.repositories.StatusRepository;
import pe.edu.upc.menteactiva.services.AppointmentService;

import java.util.List;

@Service
public class AppointmentServiceImplements implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentsRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppointmentResponseDTO create(AppointmentRequestDTO dto) {
        Clients client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Status status = statusRepository.findById(dto.getStatusId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado"));

        Schedules schedule = schedulesRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario no encontrado"));

        Appointments appointment = new Appointments();
        appointment.setClient(client);
        appointment.setStatus(status);
        appointment.setSchedule(schedule);

        return modelMapper.map(appointmentsRepository.save(appointment), AppointmentResponseDTO.class);
    }
    @Override
    public AppointmentResponseDTO update(Long id, AppointmentRequestDTO dto) {
        Appointments appointment = appointmentsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada."));
        Clients client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado."));

        Status status = statusRepository.findById(dto.getStatusId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado."));

        Schedules schedule = schedulesRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario no encontrado."));

        appointment.setClient(client);
        appointment.setStatus(status);
        appointment.setSchedule(schedule);

        return modelMapper.map(appointmentsRepository.save(appointment), AppointmentResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!appointmentsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada");
        }
        appointmentsRepository.deleteById(id);
    }

    @Override
    public List<AppointmentResponseDTO> listAll() {
        return appointmentsRepository.findAll()
                .stream()
                .map(a -> modelMapper.map(a, AppointmentResponseDTO.class))
                .toList();
    }
}
