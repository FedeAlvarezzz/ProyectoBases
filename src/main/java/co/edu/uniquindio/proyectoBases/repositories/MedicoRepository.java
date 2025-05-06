package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {
    Optional<Medico> findByCedula(String cedula);
    boolean existsByCedula(String cedula);
    boolean existsByEmail(String email);
    boolean existsByUsuario(String usuario);
}