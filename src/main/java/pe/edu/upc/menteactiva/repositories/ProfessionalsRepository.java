package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.menteactiva.entities.Profesionals;

import java.util.List;

import java.util.Optional;

public interface ProfessionalsRepository extends JpaRepository<Profesionals, Long> {
    boolean existsById(Long id);
    boolean existsByNameAndLastname(String lastname, String name);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM profesionals WHERE id = :id", nativeQuery = true)
    void deleteProfesionalsById(@Param("id") Long id);

    @Query(
            value = """
            SELECT p.name, p.lastname, COUNT(a.id) AS total_appointments
            FROM profesionals p
            JOIN schedules s ON s.profesional_id = p.id
            JOIN appointments a ON a.schedules_id = s.id
            GROUP BY p.name, p.lastname
            ORDER BY total_appointments DESC
        """,
            nativeQuery = true)
    List<Object[]> countAppointmentsByProfessional();

    Optional<Profesionals> findByUser_Id(Long userId);

    boolean existsByNameAndLastnameAndIdNot(String name, String lastname, Long id);

}
