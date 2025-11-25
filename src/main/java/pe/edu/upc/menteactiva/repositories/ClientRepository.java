package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.menteactiva.entities.Clients;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Clients, Long> {
    boolean existsById(Long id);
    boolean existsByNameAndLastname(String lastname, String name);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM clients WHERE id = :id", nativeQuery = true)
    void deleteClientById(@Param("id") Long id);

    Optional<Clients> findByUser_Id(Long userId);
}

