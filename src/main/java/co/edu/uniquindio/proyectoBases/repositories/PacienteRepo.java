package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Paciente;
import co.edu.uniquindio.proyectoBases.domain.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByCedula(Integer cedula);

    boolean existsByCedula(Integer cedula);

    boolean existsByEmail(String email);

    List<Paciente> findByEstado(EstadoUsuario estado);
}
