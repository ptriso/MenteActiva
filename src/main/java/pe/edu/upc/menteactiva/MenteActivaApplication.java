package pe.edu.upc.menteactiva;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.upc.menteactiva.dtos.request.ClientRequestDTO;
import pe.edu.upc.menteactiva.dtos.request.ProfessionalRequestDTO;
import pe.edu.upc.menteactiva.entities.Authority;
import pe.edu.upc.menteactiva.entities.Profesionals;
import pe.edu.upc.menteactiva.entities.User;
import pe.edu.upc.menteactiva.entities.User_Authority;
import pe.edu.upc.menteactiva.enums.Specialization;
import pe.edu.upc.menteactiva.repositories.AuthorityRepository;
import pe.edu.upc.menteactiva.repositories.ProfessionalsRepository;
import pe.edu.upc.menteactiva.repositories.UserRepository;
import pe.edu.upc.menteactiva.repositories.User_AuthorityRepository;
import pe.edu.upc.menteactiva.services.ClientService;
import pe.edu.upc.menteactiva.services.ProfessionalService;
import pe.edu.upc.menteactiva.services.UserService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MenteActivaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenteActivaApplication.class, args);
    }
    @Bean
    public CommandLineRunner initSecurity(
            AuthorityRepository authorityRepository,
            UserRepository userRepository,
            User_AuthorityRepository userAuthorityRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            User adminUser = userRepository.findByUsername("admin");
            if (adminUser == null) {
                adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                adminUser.setEnabled(true);
                adminUser = userRepository.save(adminUser);

                // Asignar rol ADMIN (que import.sql ya creó)
                Authority roleAdmin = authorityRepository.findByName("ROLE_ADMIN");
                if (roleAdmin != null) {
                    User_Authority ua = new User_Authority();
                    ua.setUser(adminUser);
                    ua.setAuthority(roleAdmin);
                    userAuthorityRepository.save(ua);
                    System.out.println("✅ Usuario admin creado - username: admin, password: admin123");
                }
            }
        };
    }
}
