package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.ClientRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.ClientResponseDTO;

import java.util.List;

public interface ClientService {
    ClientResponseDTO create(ClientRequestDTO dto);
    ClientResponseDTO update(Long id, ClientRequestDTO dto);
    void delete(Long id);
    List<ClientResponseDTO> listAll();
    ClientResponseDTO findById(Long id);

}
