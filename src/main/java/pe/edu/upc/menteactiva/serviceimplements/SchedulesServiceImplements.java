package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.SchedulesRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.SchedulesResponseDTO;
import pe.edu.upc.menteactiva.entities.Schedules;
import pe.edu.upc.menteactiva.repositories.ProfessionalsRepository;
import pe.edu.upc.menteactiva.repositories.SchedulesRepository;
import pe.edu.upc.menteactiva.services.SchedulesService;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class SchedulesServiceImplements implements SchedulesService {

    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    private ProfessionalsRepository profesionalsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SchedulesResponseDTO create(SchedulesRequestDTO dto) {
        var prof = profesionalsRepository.findById(dto.getProfesionalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado"));

        Schedules s = modelMapper.map(dto, Schedules.class);
        s.setId(null);
        s.setProfesional(prof);

        s = schedulesRepository.save(s);
        SchedulesResponseDTO out = modelMapper.map(s, SchedulesResponseDTO.class);
        out.setProfesionalId(s.getProfesional().getId());
        return out;
    }
    @Override
    public SchedulesResponseDTO update(Long id, SchedulesRequestDTO dto)
    {
        Schedules s = schedulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario no encontrado"));

        if (dto.getProfesionalId() != null) {
            var prof = profesionalsRepository.findById(dto.getProfesionalId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado"));
            s.setProfesional(prof);
        }
        s.setDate(dto.getDate());
        s.setTime_start(dto.getTime_start());
        s.setTime_ends(dto.getTime_ends());

        s = schedulesRepository.save(s);
        SchedulesResponseDTO out = modelMapper.map(s, SchedulesResponseDTO.class);
        out.setProfesionalId(s.getProfesional().getId());
        return out;
    }

    @Override
    public void delete(Long id) {
        if(!schedulesRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario no encontrado");
        }
        schedulesRepository.deleteById(id);
    }

    @Override
    public List<SchedulesResponseDTO> listAll() {
        return schedulesRepository.findAll().stream().map(s -> {
            SchedulesResponseDTO dto = modelMapper.map(s, SchedulesResponseDTO.class);
            dto.setProfesionalId((s.getProfesional() != null) ? s.getProfesional().getId() : null);
            return dto;
        }).toList();
    }
    @Override
    public List<SchedulesResponseDTO> getSchedulesByProfessionalId(Long profesionalId) {
        List<Schedules> schedules = schedulesRepository.findByProfesional_Id(profesionalId);
        return schedules.stream()
                .map(s -> modelMapper.map(s, SchedulesResponseDTO.class))
                .collect(Collectors.toList());
    }
}
