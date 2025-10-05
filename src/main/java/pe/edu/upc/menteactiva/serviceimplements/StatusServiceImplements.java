package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.StatusRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.StatusResponseDTO;
import pe.edu.upc.menteactiva.entities.Status;
import pe.edu.upc.menteactiva.repositories.StatusRepository;
import pe.edu.upc.menteactiva.services.StatusService;

import java.util.List;

@Service
public class StatusServiceImplements implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StatusResponseDTO create(StatusRequestDTO dto) {
        Status status = new Status();
        status.setName(dto.getName());
        status.setDescription(dto.getDescription());

        return modelMapper.map(statusRepository.save(status), StatusResponseDTO.class);
    }

    @Override
    public StatusResponseDTO update(Long id, StatusRequestDTO dto) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado"));

        status.setName(dto.getName());
        status.setDescription(dto.getDescription());

        return modelMapper.map(statusRepository.save(status), StatusResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!statusRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado");
        }
        statusRepository.deleteById(id);
    }

    @Override
    public List<StatusResponseDTO> listAll() {
        return statusRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, StatusResponseDTO.class))
                .toList();
    }
}
