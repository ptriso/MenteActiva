package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.querys.TopClientesResponseDTO;
import pe.edu.upc.menteactiva.dtos.querys.TopEspecialidadResponseDTO;
import pe.edu.upc.menteactiva.dtos.querys.TopProfesionalResponseDTO;
import pe.edu.upc.menteactiva.dtos.request.AppointmentRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentClientResponseDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentProfessionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.AppointmentResponseDTO;
import pe.edu.upc.menteactiva.entities.Appointments;
import pe.edu.upc.menteactiva.entities.Clients;
import pe.edu.upc.menteactiva.entities.Schedules;
import pe.edu.upc.menteactiva.entities.Status;
import pe.edu.upc.menteactiva.enums.StatusAp;
import pe.edu.upc.menteactiva.repositories.AppointmentRepository;
import pe.edu.upc.menteactiva.repositories.ClientRepository;
import pe.edu.upc.menteactiva.repositories.SchedulesRepository;
import pe.edu.upc.menteactiva.repositories.StatusRepository;
import pe.edu.upc.menteactiva.services.AppointmentService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public AppointmentResponseDTO create(AppointmentRequestDTO dto) {

        if (appointmentsRepository.existsNonCancelledBySchedule(dto.getScheduleId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El horario seleccionado ya fue reservado por otro usuario."
            );
        }

        Clients client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Schedules schedule = schedulesRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Horario no encontrado"));

        Status statusProgramada = statusRepository.findByStatusap(StatusAp.PROGRAMADA)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Estado PROGRAMADA no encontrado"));

        Appointments appointment = new Appointments();
        appointment.setClient(client);
        appointment.setSchedule(schedule);
        appointment.setStatus(statusProgramada);

        Appointments saved = appointmentsRepository.save(appointment);

        return modelMapper.map(saved, AppointmentResponseDTO.class);
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

    @Transactional(readOnly = true)
    @Override
    public Optional<AppointmentResponseDTO> proximaCitaDeCliente(Long clientId) {
        Optional<Appointments> opt = appointmentsRepository.findNextByClient(
                clientId, LocalDate.now(), LocalTime.now()
        );
        return opt.map(a -> modelMapper.map(a, AppointmentResponseDTO.class));
    }

    @Override
    public List<TopProfesionalResponseDTO> topProfesionalesMasCitas(int top) {
        return appointmentsRepository.topProfesionalesTodas(
                org.springframework.data.domain.PageRequest.of(0, top)
        );
    }
    @Override
    public List<TopEspecialidadResponseDTO> topEspecialidades(int top) {
        return appointmentsRepository.topEspecialidades(
                org.springframework.data.domain.PageRequest.of(0, top)
        );
    }
    @Override
    public List<TopClientesResponseDTO> topClientesMasCitasTodas(int top) {
        return appointmentsRepository.topClientesTodas(
                org.springframework.data.domain.PageRequest.of(0, top)
        );
    }

    @Override
    public List<AppointmentClientResponseDTO> listByClientId(Long clientId) {
        return appointmentsRepository.findAppointmentsByClientIdDTO(clientId);
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        Appointments appt = appointmentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        Status statusCancelada = statusRepository.findByStatusap(StatusAp.CANCELADA)
                .orElseThrow(() -> new RuntimeException("Estado CANCELADA no existe"));

        appt.setStatus(statusCancelada);
        appointmentsRepository.save(appt);
    }

    @Override
    public List<AppointmentProfessionalDTO> listByProfessional(Long idProfessional) {
        List<Appointments> appts = appointmentsRepository.findByProfessional(idProfessional);

        return appts.stream().map(a -> {
            AppointmentProfessionalDTO dto = new AppointmentProfessionalDTO();
            dto.setId(a.getId());
            dto.setClientName(a.getClient().getName());
            dto.setClientLastname(a.getClient().getLastname());
            dto.setDate(a.getSchedule().getDate());
            dto.setTimeStart(a.getSchedule().getTime_start());
            dto.setTimeEnd(a.getSchedule().getTime_ends());
            dto.setStatus(a.getStatus().getStatusap().name());
            return dto;
        }).toList();
    }

    @Override
    public List<AppointmentProfessionalDTO> listUpcomingByProfessional(Long idProfessional) {
        List<Appointments> appts = appointmentsRepository.findUpcomingByProfessional(idProfessional);

        return appts.stream().map(a -> {
            AppointmentProfessionalDTO dto = new AppointmentProfessionalDTO();
            dto.setId(a.getId());
            dto.setClientName(a.getClient().getName());
            dto.setClientLastname(a.getClient().getLastname());
            dto.setDate(a.getSchedule().getDate());
            dto.setTimeStart(a.getSchedule().getTime_start());
            dto.setTimeEnd(a.getSchedule().getTime_ends());
            dto.setStatus(a.getStatus().getStatusap().name());
            return dto;
        }).toList();
    }
    @Override
    @Transactional
    public void updateStatus(Long id, StatusAp newStatusAp) {
        Appointments appt = appointmentsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        Status status = statusRepository.findByStatusap(newStatusAp)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado"));

        appt.setStatus(status);
        appointmentsRepository.save(appt);
    }
}

