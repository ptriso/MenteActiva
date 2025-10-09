package pe.edu.upc.menteactiva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.menteactiva.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    @Query(
            value = """
            SELECT 
                u.id AS id_usuario,
                u.username AS username,
                p.name AS nombre,
                p.lastname AS apellido,
                p.specialization AS especializacion
                FROM users u
                JOIN profesionals p ON p.user_id = u.id
            """,
            nativeQuery = true)
    List<Object[]> getUsersWhoAreProfessionals();

    @Query(
            value = """
            SELECT 
                u.id AS id_usuario,
                u.username AS username,
                c.name AS nombre,
                c.lastname AS apellido,
                c.mail AS correo
                FROM users u
                JOIN clients c ON c.user_id = u.id
            """,
            nativeQuery = true)
    List<Object[]> getUsersWhoAreClients();
}
