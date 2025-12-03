package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.AuthorityRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.AuthorityResponseDTO;

import java.util.List;

public interface AuthorityService {

    AuthorityResponseDTO create (AuthorityRequestDTO dto);
    AuthorityResponseDTO update (Long id, AuthorityRequestDTO dto);
    void delete(Long id);
    List<AuthorityResponseDTO> GetAllAuthorities();
}
