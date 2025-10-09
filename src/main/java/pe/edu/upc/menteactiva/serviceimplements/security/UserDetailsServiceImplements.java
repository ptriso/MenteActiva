package pe.edu.upc.menteactiva.serviceimplements.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.menteactiva.entities.User;
import pe.edu.upc.menteactiva.security.UserSecurity;
import pe.edu.upc.menteactiva.services.UserService;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFound = userService.findByUsername(username);
        if (userFound == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserSecurity(userFound);
    }
}
