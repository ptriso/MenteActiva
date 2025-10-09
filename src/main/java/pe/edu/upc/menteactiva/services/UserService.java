package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.UserRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.NativeQuery_UserClientDTO;
import pe.edu.upc.menteactiva.dtos.responses.NativeQuery_UserProfessionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.UserResponseDTO;
import pe.edu.upc.menteactiva.entities.User;

import java.util.List;

public interface UserService {

    UserResponseDTO create(UserRequestDTO dto);
    UserResponseDTO update(Long id, UserRequestDTO dto);
    void delete(Long id);
    public List<UserResponseDTO> GetAllUsers();

    List<NativeQuery_UserProfessionalDTO> getUsersWhoAreProfessionals();

    List<NativeQuery_UserClientDTO> getUsersWhoAreClients();
}
