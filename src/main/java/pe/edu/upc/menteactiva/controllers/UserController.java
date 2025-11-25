package pe.edu.upc.menteactiva.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.menteactiva.dtos.request.UserRequestDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_UserClientDTO;
import pe.edu.upc.menteactiva.dtos.querys.NativeQuery_UserProfessionalDTO;
import pe.edu.upc.menteactiva.dtos.responses.UserResponseDTO;
import pe.edu.upc.menteactiva.dtos.security.DTOToken;
import pe.edu.upc.menteactiva.dtos.security.DTOUser;
import pe.edu.upc.menteactiva.entities.User;
import pe.edu.upc.menteactiva.repositories.ClientRepository;
import pe.edu.upc.menteactiva.repositories.ProfessionalsRepository;
import pe.edu.upc.menteactiva.security.JwtUtilService;
import pe.edu.upc.menteactiva.security.UserSecurity;
import pe.edu.upc.menteactiva.services.UserService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/MenteActiva/User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProfessionalsRepository professionalsRepository;

    // Endpoint de Login
    @PostMapping("/login")
    public ResponseEntity<DTOToken> login(@RequestBody DTOUser credentials) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
            );

            UserSecurity userSecurity = (UserSecurity) userDetailsService
                    .loadUserByUsername(credentials.getUsername());

            String jwt = jwtUtilService.generateToken(userSecurity);

            // --- LÓGICA MODIFICADA ---
            Long userId = userSecurity.getUser().getId();
            String authorities = userSecurity.getAuthorities().stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.joining(";"));

            // --- NUEVA LÓGICA PARA BUSCAR EL ID DEL PERFIL ---
            Long profileId = null;
            if (authorities.contains("ROLE_CLIENT")) {
                profileId = clientRepository.findByUser_Id(userId)
                        .map(client -> client.getId())
                        .orElse(null); // Devuelve el client_id
            } else if (authorities.contains("ROLE_PROFESSIONAL")) {
                profileId = professionalsRepository.findByUser_Id(userId)
                        .map(prof -> prof.getId())
                        .orElse(null); // Devuelve el professional_id
            }

            DTOToken response = new DTOToken(jwt, userId, userSecurity.getUsername(), authorities, profileId);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    // Endpoint de Registro
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DTOUser dtoUser) {
        try {
            System.out.println("📝 Iniciando registro: " + dtoUser.getUsername());

            // Si no especifica authorities, usar ROLE_USER
            if (dtoUser.getAuthorities() == null || dtoUser.getAuthorities().isBlank()) {
                dtoUser.setAuthorities("ROLE_USER");
            }

            // Registrar usuario (esto funciona correctamente según los logs)
            User newUser = userService.addUser(dtoUser);

            // Construir respuesta manualmente SIN consultar la BD nuevamente
            List<String> authoritiesList = Arrays.asList(dtoUser.getAuthorities().split(";"));

            UserResponseDTO response = new UserResponseDTO(
                    newUser.getId(),
                    newUser.getUsername(),
                    newUser.isEnabled(),
                    authoritiesList
            );

            System.out.println("Usuario registrado exitosamente con ID: " + newUser.getId());

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error interno del servidor");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") Long id) {
        try {
            UserResponseDTO user = userService.findByIdDTO(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/registrar")
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    @GetMapping("/listartodos")
    public List<UserResponseDTO>  GetAllUsers()
    {
        return userService.GetAllUsers();
    }

    @PutMapping("/modificar/{id}")
    public  ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/UsuariosProfesionales")
    public ResponseEntity<List<NativeQuery_UserProfessionalDTO>> getUsersWhoAreProfessionals() {
        return ResponseEntity.ok(userService.getUsersWhoAreProfessionals());
    }

    @GetMapping("/UsuariosClientes")
    public ResponseEntity<List<NativeQuery_UserClientDTO>> getUsersWhoAreClients() {
        return ResponseEntity.ok(userService.getUsersWhoAreClients());
    }
}
