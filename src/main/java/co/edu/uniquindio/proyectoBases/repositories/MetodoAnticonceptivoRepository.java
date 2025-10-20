package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.MetodoAnticonceptivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MetodoAnticonceptivoRepository extends JpaRepository<MetodoAnticonceptivo, String> {
    Optional<MetodoAnticonceptivo> findByIdMetodoAnticonceptivo(String idMetodoAnticonceptivo);
    boolean existsByIdMetodoAnticonceptivo(String idMetodoAnticonceptivo);
}