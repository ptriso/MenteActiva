package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.menteactiva.entities.User_Authority;

import java.util.List;
import java.util.Optional;

public interface User_AuthorityRepository extends JpaRepository<User_Authority, Long> {
    Optional<User_Authority> findByUserIdAndAuthorityId(Long userId, Long authorityId);
    boolean existsByUserIdAndAuthorityId(Long userId, Long authorityId);

}
