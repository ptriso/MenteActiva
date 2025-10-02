package pe.edu.upc.menteactiva.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.menteactiva.entities.User_Authority;
import pe.edu.upc.menteactiva.repositories.User_AuthorityRepository;
import pe.edu.upc.menteactiva.services.User_AuthorityService;

import java.util.List;

@Service
public class User_AuthorityServiceImplements implements User_AuthorityService {

    @Autowired
    User_AuthorityRepository user_AuthorityRepository;

    @Override
    public List<User_Authority> GetAllUser_Authorities() {
        return user_AuthorityRepository.findAll();
    }
}
