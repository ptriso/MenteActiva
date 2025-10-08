package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.User_AuthorityRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.User_AuthorityResponseDTO;
import pe.edu.upc.menteactiva.entities.Authority;
import pe.edu.upc.menteactiva.entities.User;
import pe.edu.upc.menteactiva.entities.User_Authority;
import pe.edu.upc.menteactiva.repositories.AuthorityRepository;
import pe.edu.upc.menteactiva.repositories.UserRepository;
import pe.edu.upc.menteactiva.repositories.User_AuthorityRepository;
import pe.edu.upc.menteactiva.services.User_AuthorityService;

import java.util.List;

@Service
public class User_AuthorityServiceImplements implements User_AuthorityService {

    @Autowired
    User_AuthorityRepository user_AuthorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public User_AuthorityResponseDTO create(User_AuthorityRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Authority authority = authorityRepository.findById(dto.getAuthorityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autoridad no encontrada"));

        if (user_AuthorityRepository.existsByUserIdAndAuthorityId(user.getId(), authority.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ya tiene esta autoridad asignada");
        }

        User_Authority ua = new User_Authority();
        ua.setUser(user);
        ua.setAuthority(authority);

        return modelMapper.map(user_AuthorityRepository.save(ua), User_AuthorityResponseDTO.class);
    }

    @Override
    public User_AuthorityResponseDTO update(Long id, User_AuthorityRequestDTO dto)
    {
        User_Authority ua = user_AuthorityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Relación no encontrada"));

        User user= userRepository.findById(dto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        Authority auth = authorityRepository.findById(dto.getAuthorityId()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Autoridad no encontrada"));

        boolean existsCombo = user_AuthorityRepository.existsByUserIdAndAuthorityId(user.getId(), auth.getId());
        if (existsCombo && !(ua.getUser().getId().equals(user.getId()) && ua.getAuthority().getId().equals(auth.getId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe la relación usuario-authority");
        }

        ua.setUser(user);
        ua.setAuthority(auth);

        return modelMapper.map(user_AuthorityRepository.save(ua), User_AuthorityResponseDTO.class);
    }
    @Override
    public void remove(Long id) {
        if (!user_AuthorityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada");
        }
        user_AuthorityRepository.deleteById(id);
    }

    @Override
    public List<User_AuthorityResponseDTO> listAll() {
        return user_AuthorityRepository.findAll()
                .stream()
                .map(ua -> modelMapper.map(ua, User_AuthorityResponseDTO.class))
                .toList();
    }
}
