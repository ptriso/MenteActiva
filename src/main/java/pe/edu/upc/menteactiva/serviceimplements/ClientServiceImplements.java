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
import pe.edu.upc.menteactiva.repositories.ClientRepository;
import pe.edu.upc.menteactiva.repositories.UserRepository;
import pe.edu.upc.menteactiva.services.ClientService;

import java.util.List;

@Service
public class ClientServiceImplements implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientResponseDTO create(ClientRequestDTO dto){

        String name = dto.getName().trim();
        String lastname = dto.getLastname().trim();
        if(!userRepository.existsById(dto.getUserId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        if (clientRepository.existsByNameAndLastname(lastname, name)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cliente con ese nombre y apellido");
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
}
