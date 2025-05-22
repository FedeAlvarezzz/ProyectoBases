package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    Examen findByIdExamen(Long idExamen);
    boolean existsByIdExamen(Long idExamen);
    //lista por Exámenes con Datos de Consulta
    @Query("SELECT e FROM Examen e JOIN FETCH e.consulta c JOIN FETCH c.paciente p ORDER BY e.fechaResultado DESC")
    List<Examen> findAllWithConsulta();
}