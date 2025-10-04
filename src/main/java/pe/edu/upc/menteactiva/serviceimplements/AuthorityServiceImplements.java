package pe.edu.upc.menteactiva.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.menteactiva.dtos.request.AuthorityRequestDTO;
import pe.edu.upc.menteactiva.dtos.responses.AuthorityResponseDTO;
import pe.edu.upc.menteactiva.entities.Authority;
import pe.edu.upc.menteactiva.repositories.AuthorityRepository;
import pe.edu.upc.menteactiva.services.AuthorityService;

import java.util.List;

@Service
public class AuthorityServiceImplements implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public AuthorityResponseDTO create(AuthorityRequestDTO dto)
    {
        if(authorityRepository.existsByName(dto.getName()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Auditoria ya existente");
        }
        Authority authority = modelMapper.map(dto, Authority.class);
        authority.setId(null);
        return  modelMapper.map(authorityRepository.save(authority), AuthorityResponseDTO.class);
    }
    @Override
    public AuthorityResponseDTO update(Long id, AuthorityRequestDTO dto)
    {
        Authority authority = authorityRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la Auditoria"));
        authority.setName(dto.getName());
        return  modelMapper.map(authorityRepository.save(authority), AuthorityResponseDTO.class);
    }

    public void delete(Long id)
    {
        if(!authorityRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el Auditoria");
        }
        authorityRepository.deleteById(id);
    }
    @Override
    public List<AuthorityResponseDTO> GetAllAuthorities() {
        return authorityRepository.findAll().stream()
                .map(authority -> modelMapper.map(authority, AuthorityResponseDTO.class))
                .toList();
    }

}
