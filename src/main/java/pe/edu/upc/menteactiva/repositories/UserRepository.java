package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
