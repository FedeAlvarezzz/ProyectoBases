package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Paciente;
import co.edu.uniquindio.proyectoBases.domain.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente, Integer> {

    Paciente findByCedula(Integer cedula);

    boolean existsByCedula(Integer cedula);

    boolean existsByEmail(String email);

    List<Paciente> findByEstado(EstadoUsuario estado);
 //lista los pacientes
    @Query("SELECT p FROM Paciente p")
    List<Paciente> findAllPacientes();
// lista por Pacientes con Datos de Consulta
    @Query("SELECT p FROM Paciente p WHERE p.cedula IN " +
            "(SELECT DISTINCT c.paciente.cedula FROM Consulta c WHERE c.fecha >= :fechaInicio)")
    List<Paciente> findPacientesConConsultasRecientes(LocalDate fechaInicio);

}
