package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.ProfessionalRequestDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_TotalCitasPorProfesionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.ProfessionalResponseDTO;
import pe.edu.upc.menteactiva.entities.Profesionals;
import pe.edu.upc.menteactiva.repositories.ProfessionalsRepository;
import pe.edu.upc.menteactiva.repositories.UserRepository;
import pe.edu.upc.menteactiva.services.ProfessionalService;

import java.util.List;

@Service
public class ProfessionalServiceImplements implements ProfessionalService {

    @Autowired
    private ProfessionalsRepository professionalsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProfessionalResponseDTO create(ProfessionalRequestDTO dto) {

        String name = dto.getName().trim();
        String lastname = dto.getLastname().trim();
        if(!userRepository.existsById(dto.getUserId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        if (professionalsRepository.existsByNameAndLastname(lastname, name)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un profesional con ese nombre y apellido");
        }
        Profesionals p = modelMapper.map(dto, Profesionals.class);
        p.setId(null);
        p.setName(name);
        p.setLastname(lastname);
        return modelMapper.map(professionalsRepository.save(p), ProfessionalResponseDTO.class);
    }
    @Override
    public ProfessionalResponseDTO update(Long id, ProfessionalRequestDTO dto) {
        Profesionals p = professionalsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado"));

        if(!userRepository.existsById(dto.getUserId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        String name = dto.getName().trim();
        String lastname = dto.getLastname().trim();

        if (professionalsRepository.existsByNameAndLastname(name, lastname)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe otro profesional con ese nombre y apellido");
        }

        p.setName(dto.getName());
        p.setLastname(dto.getLastname());
        p.setSpecialization(dto.getSpecialization());
        p.setMail(dto.getMail());
        p.setPhone(dto.getPhone());

        return modelMapper.map(professionalsRepository.save(p), ProfessionalResponseDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!professionalsRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado");
        }
        professionalsRepository.deleteProfesionalsById(id);
    }
    @Override
    public List<ProfessionalResponseDTO>listAll()
    {
        return professionalsRepository.findAll().stream().map(p -> modelMapper.map(p, ProfessionalResponseDTO.class)).toList();
    }

    @Override
    public List<NativeQuery_TotalCitasPorProfesionalDTO> countAppointmentsByProfessional() {
        return professionalsRepository.countAppointmentsByProfessional()
                .stream()
                .map(obj -> new NativeQuery_TotalCitasPorProfesionalDTO(
                        (String) obj[0],                // nombre
                        (String) obj[1],                // apellido
                        ((Number) obj[2]).longValue()   // cantidad de citas
                ))
                .toList();
    }
    @Override
    public ProfessionalResponseDTO listId(Long id) {

        Profesionals profesional = professionalsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado con id: " + id));

        return modelMapper.map(profesional, ProfessionalResponseDTO.class);
    }


}
