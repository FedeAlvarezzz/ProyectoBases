package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, String> {
    Optional<Examen> findByIdExamen(String idExamen);
    boolean existsByIdExamen(String idExamen);
}