package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.menteactiva.entities.Profesionals;
import pe.edu.upc.menteactiva.enums.Specialization;

import java.util.List;

public interface ProfessionalsRepository extends JpaRepository<Profesionals, Long> {
    boolean existsById(Long id);
    boolean existsByNameAndLastname(String lastname, String name);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM profesionals WHERE id = :id", nativeQuery = true)
    void deleteProfesionalsById(@Param("id") Long id);


}
