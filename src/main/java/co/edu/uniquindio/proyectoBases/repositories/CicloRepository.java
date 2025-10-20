package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, Integer> {
    Optional<Ciclo> findByIdCiclo(int idCiclo);
    boolean existsByIdCiclo(int idCiclo);
}