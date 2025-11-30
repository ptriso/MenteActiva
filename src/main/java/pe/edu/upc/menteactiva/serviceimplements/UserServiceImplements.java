package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.UserRequestDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_UserClientDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_UserProfessionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.UserResponseDTO;
import pe.edu.upc.menteactiva.dtos.security.DTOUser;
import pe.edu.upc.menteactiva.entities.Authority;
import pe.edu.upc.menteactiva.entities.User;
import pe.edu.upc.menteactiva.entities.User_Authority;
import pe.edu.upc.menteactiva.repositories.AuthorityRepository;
import pe.edu.upc.menteactiva.repositories.UserRepository;
import pe.edu.upc.menteactiva.repositories.User_AuthorityRepository;
import pe.edu.upc.menteactiva.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public
class UserServiceImplements implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private User_AuthorityRepository userAuthorityRepository;

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

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with Id: " + id + " not found"));
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with Username: " + username + " not found");
        }
        return user;
    }

    @Override
    public UserResponseDTO findByIdDTO(Long id) {
        User user = findById(id);

        List<String> authorities = user.getUser_authority()
                .stream()
                .map(ua -> ua.getAuthority().getName())
                .collect(Collectors.toList());

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.isEnabled(),
                authorities
        );
    }

    // Método auxiliar para convertir string de authorities a lista
    private List<Authority> authoritiesFromString(String authoritiesStr) {
        List<Authority> authoritiesList = new ArrayList<>();
        List<String> authoritiesNames = Arrays.asList(authoritiesStr.split(";"));

        for (String authorityName : authoritiesNames) {
            Authority authority = authorityRepository.findByName(authorityName.trim());
            if (authority != null) {
                authoritiesList.add(authority);
            }
        }

        return authoritiesList;
    }

    @Override
    public User addUser(DTOUser dtoUser) {
        // Crear usuario
        User newUser = new User();
        newUser.setUsername(dtoUser.getUsername());
        newUser.setPassword(dtoUser.getPassword());
        newUser.setEnabled(true);

        // Convertir authorities
        List<Authority> authorities = authoritiesFromString(dtoUser.getAuthorities());

        return addUser(newUser, authorities);
    }

    @Override
    public User addUser(User user) {
        // Por defecto, asignar rol ROLE_USER
        Authority defaultAuthority = authorityRepository.findByName("ROLE_USER");
        return addUser(user, List.of(defaultAuthority));
    }

    // Método privado para registrar usuario con authorities
    private User addUser(User user, List<Authority> authorities) {
        // Validar que username no exista
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username: " + user.getUsername() + " is already registered");
        }

        // Validar password (puedes agregar más validaciones)
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password must be at least 6 characters");
        }

        // Encriptar password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(true);

        // Guardar usuario
        User savedUser = userRepository.save(user);

        // Crear relaciones User_Authority
        for (Authority authority : authorities) {
            User_Authority userAuthority = new User_Authority();
            userAuthority.setUser(savedUser);
            userAuthority.setAuthority(authority);
            userAuthorityRepository.save(userAuthority);
        }

        return savedUser;
    }
    @Override
    public User findByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email is required"
            );
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with Email: " + email + " not found"
            );
        }

        return user;
    }
}

