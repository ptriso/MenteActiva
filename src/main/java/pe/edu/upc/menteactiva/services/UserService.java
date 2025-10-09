package pe.edu.upc.menteactiva.services;

import pe.edu.upc.menteactiva.dtos.request.UserRequestDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_UserClientDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_UserProfessionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.UserResponseDTO;
import pe.edu.upc.menteactiva.dtos.security.DTOUser;
import pe.edu.upc.menteactiva.entities.User;

import java.util.List;

public interface UserService {

    UserResponseDTO create(UserRequestDTO dto);
    UserResponseDTO update(Long id, UserRequestDTO dto);
    void delete(Long id);
    public List<UserResponseDTO> GetAllUsers();

    List<NativeQuery_UserProfessionalDTO> getUsersWhoAreProfessionals();

    List<NativeQuery_UserClientDTO> getUsersWhoAreClients();

    // ... security
    User findById(Long id);
    User findByUsername(String username);
    UserResponseDTO findByIdDTO(Long id);
    User addUser(DTOUser dtoUser);
    User addUser(User user);
    // ... tus otros métodos
}
