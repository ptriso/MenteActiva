package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.ConsentsRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ConsentsResponseDTO;
import pe.edu.upc.menteactiva.entities.Consents;
import pe.edu.upc.menteactiva.repositories.ClientsRepository;
import pe.edu.upc.menteactiva.repositories.ConsentsRepository;
import pe.edu.upc.menteactiva.services.ConsentsService;

import java.util.List;

@Service
public class ConsentsServiceImplements implements ConsentsService {
    @Autowired
    private ConsentsRepository consentsRepository;
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ConsentsResponseDTO create(ConsentsRequestDTO dto)
    {
        if(!clientsRepository.existsById(dto.getClientId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }

        Consents c = modelMapper.map(dto, Consents.class);
        c.setId(null);
        return modelMapper.map(consentsRepository.save(c), ConsentsResponseDTO.class);
    }

    @Override
    public ConsentsResponseDTO update(Long id, ConsentsRequestDTO dto)
    {
        Consents c = consentsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consentimiento no encontrado"));

        if(!clientsRepository.existsById(dto.getClientId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        c.setAge(dto.getAge());
        c.setDoc_consent(dto.getDoc_consent());
        return modelMapper.map(consentsRepository.save(c), ConsentsResponseDTO.class);
    }

    @Override
    public void delete(Long id)
    {
        if (!consentsRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consentimiento no encontrado");
        }
        consentsRepository.deleteById(id);
    }

    @Override
    public List<ConsentsResponseDTO>listAll()
    {
        return consentsRepository.findAll().stream().map(c -> modelMapper.map(c, ConsentsResponseDTO.class)).toList();
    }
}
