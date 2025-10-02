package pe.edu.upc.menteactiva.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.menteactiva.entities.User;
import pe.edu.upc.menteactiva.repositories.UserRepository;
import pe.edu.upc.menteactiva.services.UserService;

import java.util.List;

@Service
public class UserServiceImplements implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }
}
