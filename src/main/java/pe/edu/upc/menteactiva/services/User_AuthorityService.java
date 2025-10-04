package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.User_AuthorityRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.User_AuthorityResponseDTO;
import pe.edu.upc.menteactiva.entities.User_Authority;

import java.util.List;

public interface User_AuthorityService {

    //User_AuthorityResponseDTO add(User_AuthorityRequestDTO dto);

    public List<User_Authority> GetAllUser_Authorities();
}
