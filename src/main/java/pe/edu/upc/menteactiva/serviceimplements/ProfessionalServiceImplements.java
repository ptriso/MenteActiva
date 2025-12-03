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
import pe.edu.upc.menteactiva.enums.Specialization;
import pe.edu.upc.menteactiva.repositories.ClientRepository;
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

    @Autowired
    private ClientRepository clientRepository;

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

        boolean existsOther = professionalsRepository
                .existsByNameAndLastnameAndIdNot(name, lastname, id);

        if (existsOther) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe otro profesional con ese nombre y apellido"
            );
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
                        (String) obj[0],
                        (String) obj[1],
                        ((Number) obj[2]).longValue()
                ))
                .toList();
    }
    @Override
    public ProfessionalResponseDTO listId(Long id) {

        Profesionals profesional = professionalsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado con id: " + id));

        return modelMapper.map(profesional, ProfessionalResponseDTO.class);
    }
    @Override
    public void createIfNotExists(Long userId) {

        if (professionalsRepository.findByUser_Id(userId).isPresent()) {
            return;
        }

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        var clientOpt = clientRepository.findByUser_Id(userId);

        Profesionals p = new Profesionals();
        p.setUser(user);

        if (clientOpt.isPresent()) {
            var c = clientOpt.get();
            p.setName(c.getName());
            p.setLastname(c.getLastname());
            p.setMail(c.getMail());
            p.setPhone(c.getPhone());
        } else {
            p.setName("");
            p.setLastname("");
            p.setMail("");
            p.setPhone("");
        }

        p.setSpecialization(Specialization.NONE);

        professionalsRepository.save(p);
    }
}
