package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.User_Authority;

import java.util.List;

public interface User_AuthorityRepository extends JpaRepository<User_Authority, Long> {
    //List<User_Authority> findByUserId(Long userId);
    //void deleteByUserId(Long userId);
    //void deleteByUserIdAndAuthorityId(Long userId, Long authorityId);

}
