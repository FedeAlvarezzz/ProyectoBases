package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository <Receta, Long> {


    Receta findByIdReceta(Long idReceta);

    @Query("SELECT r FROM Receta r WHERE r.paciente.cedula = :pacienteId")
    List<Receta> findByPacienteId(@Param("pacienteId") Long pacienteId);


}