package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Ciclo;
import co.edu.uniquindio.proyectoBases.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, Integer> {

    boolean existsByIdCiclo(int idCiclo);

    Ciclo findByIdCiclo(int idCiclo);
//lista por Ciclos con Datos de Paciente
    @Query("SELECT c FROM Ciclo c JOIN FETCH c.paciente p ORDER BY p.apellido, c.fechaInicio DESC")
    List<Ciclo> findAllWithPaciente();
// lista por Ciclos por Paciente
    @Query("SELECT c.paciente FROM Ciclo c WHERE c.paciente.cedula IN " +
            "(SELECT c2.paciente.cedula FROM Ciclo c2 GROUP BY c2.paciente.cedula " +
            "HAVING MAX(c2.duracion) - MIN(c2.duracion) > 5) " +
            "GROUP BY c.paciente.cedula")
    List<Paciente> findPacientesConCiclosIrregulares();
}