package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.menteactiva.entities.Appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointments, Long> {
    boolean existsById(Long id);

    // Próxima cita del cliente: (fecha futura) o (hoy y hora futura)
    @Query("""
        SELECT a
        FROM Appointments a
        JOIN a.schedule s
        WHERE a.client.id = :clientId
          AND ( s.date > :today OR (s.date = :today AND s.time_start > :now) )
        ORDER BY s.date ASC, s.time_start ASC
    """)
    Optional<Appointments> findNextByClient(
            @Param("clientId") Long clientId,
            @Param("today") LocalDate today,
            @Param("now") LocalTime now
    );

    @Query("""
  SELECT new pe.edu.upc.menteactiva.dtos.querys.TopProfesionalResponseDTO(
      p.id, p.lastname, p.name, COUNT(a)
  )
  FROM Appointments a
  JOIN a.schedule s
  JOIN s.profesional p
  GROUP BY p.id, p.lastname, p.name
  ORDER BY COUNT(a) DESC
""")
    List<pe.edu.upc.menteactiva.dtos.querys.TopProfesionalResponseDTO> topProfesionalesTodas(
            org.springframework.data.domain.Pageable page);

    @Query("""
  SELECT new pe.edu.upc.menteactiva.dtos.querys.TopEspecialidadResponseDTO(
      p.specialization,
      COUNT(a)
  )
  FROM Appointments a
  JOIN a.schedule s
  JOIN s.profesional p
  GROUP BY p.specialization
  ORDER BY COUNT(a) DESC
""")
    List<pe.edu.upc.menteactiva.dtos.querys.TopEspecialidadResponseDTO>
    topEspecialidades(org.springframework.data.domain.Pageable page);

    @Query("""
  SELECT new pe.edu.upc.menteactiva.dtos.querys.TopClientesResponseDTO(
      c.id, c.lastname, c.name, COUNT(a)
  )
  FROM Appointments a
  JOIN a.client c
  GROUP BY c.id, c.lastname, c.name
  ORDER BY COUNT(a) DESC
""")
    List<pe.edu.upc.menteactiva.dtos.querys.TopClientesResponseDTO> topClientesTodas(
            org.springframework.data.domain.Pageable page);
}
