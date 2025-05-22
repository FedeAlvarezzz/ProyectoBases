package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Paciente;
import co.edu.uniquindio.proyectoBases.domain.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByCedula(Integer cedula);

    boolean existsByCedula(Integer cedula);

    boolean existsByEmail(String email);

    List<Paciente> findByEstado(EstadoUsuario estado);

    @Query("SELECT p FROM Paciente p WHERE p.estado = :estado ORDER BY p.apellido, p.nombre")
    List<Paciente> findPacientesByEstado(@Param("estado") EstadoUsuario estado);

    @Query("SELECT p FROM Paciente p WHERE p.nombre LIKE %:nombre% OR p.apellido LIKE %:apellido%")
    List<Paciente> findPacientesByNombreOrApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);
}
