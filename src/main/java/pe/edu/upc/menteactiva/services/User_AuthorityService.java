package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.User_AuthorityRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.User_AuthorityResponseDTO;

import java.util.List;

public interface User_AuthorityService {

    User_AuthorityResponseDTO create(User_AuthorityRequestDTO dto);
    User_AuthorityResponseDTO update(Long id, User_AuthorityRequestDTO dto);
    void remove(Long id);
    List<User_AuthorityResponseDTO> listAll();
}
