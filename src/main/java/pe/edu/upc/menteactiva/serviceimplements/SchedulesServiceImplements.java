package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.SchedulesRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.SchedulesResponseDTO;
import pe.edu.upc.menteactiva.entities.Schedules;
import pe.edu.upc.menteactiva.repositories.ProfesionalsRepository;
import pe.edu.upc.menteactiva.repositories.SchedulesRepository;
import pe.edu.upc.menteactiva.services.SchedulesService;

import java.util.List;

@Service
public class SchedulesServiceImplements implements SchedulesService {

    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    private ProfesionalsRepository profesionalsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SchedulesResponseDTO create(SchedulesRequestDTO dto) {
        if(!profesionalsRepository.existsById(dto.getProfesionalId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado");
        }
        Schedules s = modelMapper.map(dto, Schedules.class);
        s.setId(null);
        return modelMapper.map(schedulesRepository.save(s), SchedulesResponseDTO.class);
    }
    @Override
    public SchedulesResponseDTO update(Long id, SchedulesRequestDTO dto)
    {
        Schedules s = schedulesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario no encontrado"));

        if(!profesionalsRepository.existsById(dto.getProfesionalId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado");
        }
        s.setDate(dto.getDate());
        s.setTime_start(dto.getTime_start());
        s.setTime_ends(dto.getTime_ends());

        return  modelMapper.map(schedulesRepository.save(s), SchedulesResponseDTO.class);
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
        return schedulesRepository.findAll().stream().map(s-> modelMapper.map(s, SchedulesResponseDTO.class)).toList();
    }
}
