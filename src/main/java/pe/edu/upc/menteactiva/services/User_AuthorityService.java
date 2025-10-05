package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.User_AuthorityRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.User_AuthorityResponseDTO;
import pe.edu.upc.menteactiva.entities.User_Authority;

import java.util.List;

public interface User_AuthorityService {

    User_AuthorityResponseDTO assign(User_AuthorityRequestDTO dto);
    void remove(Long id);
    List<User_AuthorityResponseDTO> listAll();
}
