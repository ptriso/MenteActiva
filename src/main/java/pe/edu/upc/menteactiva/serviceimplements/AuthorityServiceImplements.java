package pe.edu.upc.menteactiva.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.menteactiva.entities.Authority;
import pe.edu.upc.menteactiva.repositories.AuthorityRepository;
import pe.edu.upc.menteactiva.services.AuthorityService;

import java.util.List;

@Service
public class AuthorityServiceImplements implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public List<Authority> GetAllAuthorities() {
        return authorityRepository.findAll();
    }

}
