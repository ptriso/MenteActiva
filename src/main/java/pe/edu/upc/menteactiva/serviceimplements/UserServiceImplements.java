package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.UserRequestDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_UserClientDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_UserProfessionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.UserResponseDTO;
import pe.edu.upc.menteactiva.entities.User;
import pe.edu.upc.menteactiva.repositories.UserRepository;
import pe.edu.upc.menteactiva.services.UserService;

import java.util.List;

@Service
public class UserServiceImplements implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserResponseDTO create (UserRequestDTO dto)
    {
        if(userRepository.existsByUsername(dto.getUsername()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"nombre ya existente");
        }
        User user = modelMapper.map(dto, User.class);
        user.setId(null);

        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO update (Long id, UserRequestDTO dto)
    {
        User user = userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEnabled(dto.getEnabled());
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public void delete(Long id)
    {
        if(!userRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> GetAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEnabled(user.isEnabled());

            List<String> roles = user.getUser_authority().stream()
                    .map(ua -> ua.getAuthority().getName())
                    .toList();
            dto.setAuthorities(roles);

            return dto;
        }).toList();
    }

    @Override
    public List<NativeQuery_UserProfessionalDTO> getUsersWhoAreProfessionals() {
        return userRepository.getUsersWhoAreProfessionals()
                .stream()
                .map(obj -> new NativeQuery_UserProfessionalDTO(
                        ((Number) obj[0]).longValue(), // idUsuario
                        (String) obj[1],               // username
                        (String) obj[2],               // nombre
                        (String) obj[3],               // apellido
                        (String) obj[4]                // especialización
                ))
                .toList();
    }

    @Override
    public List<NativeQuery_UserClientDTO> getUsersWhoAreClients() {
        return userRepository.getUsersWhoAreClients()
                .stream()
                .map(obj -> new NativeQuery_UserClientDTO(
                        ((Number) obj[0]).longValue(), // idUsuario
                        (String) obj[1],               // username
                        (String) obj[2],               // nombre
                        (String) obj[3],               // apellido
                        (String) obj[4]                // correo
                ))
                .toList();
    }
}
