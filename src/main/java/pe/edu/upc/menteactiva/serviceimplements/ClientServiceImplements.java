package pe.edu.upc.menteactiva.serviceimplements;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.ClientRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ClientResponseDTO;
import pe.edu.upc.menteactiva.entities.Clients;
import pe.edu.upc.menteactiva.repositories.AppointmentRepository;
import pe.edu.upc.menteactiva.repositories.ClientRepository;
import pe.edu.upc.menteactiva.repositories.ProfessionalsRepository;
import pe.edu.upc.menteactiva.repositories.UserRepository;
import pe.edu.upc.menteactiva.services.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplements implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ProfessionalsRepository professionalsRepository;

    @Override
    public ClientResponseDTO create(ClientRequestDTO dto){

        if (dto.getUserId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "userId es obligatorio para registrar un cliente"
            );
        }


        String name = dto.getName() != null ? dto.getName().trim() : null;
        String lastname = dto.getLastname() != null ? dto.getLastname().trim() : null;
        if (!userRepository.existsById(dto.getUserId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuario no encontrado con id: " + dto.getUserId()
            );
        }
        if (clientRepository.existsByNameAndLastname(lastname, name)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un cliente con ese nombre y apellido"
            );
        }

        Clients c = modelMapper.map(dto, Clients.class);
        c.setId(null);
        c.setName(name);
        c.setLastname(lastname);
        return modelMapper.map(clientRepository.save(c), ClientResponseDTO.class);
    }

    @Override
    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
        Clients c = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        if(!userRepository.existsById(dto.getUserId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        String name = dto.getName().trim();
        String lastname = dto.getLastname().trim();

        if (clientRepository.existsByNameAndLastname(name, lastname)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe otro cliente con ese nombre y apellido");
        }
        c.setName(dto.getName());
        c.setLastname(dto.getLastname());
        c.setMail(dto.getMail());
        c.setPhone(dto.getPhone());

        return modelMapper.map(clientRepository.save(c), ClientResponseDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        if(!clientRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        clientRepository.deleteClientById(id);
    }

    @Override
    public List<ClientResponseDTO> listAll() {
        return clientRepository.findAll().stream().map( c -> modelMapper.map(c, ClientResponseDTO.class)).toList();
    }
    @Override
    public ClientResponseDTO findById(Long id) {
        Clients client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")
                );

        return modelMapper.map(client, ClientResponseDTO.class);
    }

    @Override
    public void createIfNotExists(Long userId) {

        if (clientRepository.findByUser_Id(userId).isPresent()) {
            return;
        }

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        var profesionalOpt = professionalsRepository.findByUser_Id(userId);

        Clients c = new Clients();
        c.setUser(user);

        if (profesionalOpt.isPresent()) {
            var p = profesionalOpt.get();
            c.setName(p.getName());
            c.setLastname(p.getLastname());
            c.setMail(p.getMail());
            c.setPhone(p.getPhone());
        } else {
            c.setName("");
            c.setLastname("");
            c.setMail("");
            c.setPhone("");
        }

        clientRepository.save(c);
    }

    @Override
    public List<ClientResponseDTO> listByProfessional(Long idProfessional) {
        List<Clients> clients = appointmentRepository.findClientsByProfessionalId(idProfessional);

        return clients.stream()
                .map(c -> modelMapper.map(c, ClientResponseDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public ClientResponseDTO getById(Long id) {
        Clients client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        ClientResponseDTO dto = modelMapper.map(client, ClientResponseDTO.class);

        if (client.getUser() != null) {
            dto.setUserId(client.getUser().getId());
        }
        if (client.getConsents() != null) {
            dto.setHasConsent(true);
            dto.setConsentAge(client.getConsents().getAge());
            dto.setConsentDocument(client.getConsents().getDoc_consent());

            dto.setAge(client.getConsents().getAge());
        } else {
            dto.setHasConsent(false);
            dto.setConsentAge(null);
            dto.setConsentDocument(null);
            dto.setAge(null);
        }

        return dto;
    }
}
