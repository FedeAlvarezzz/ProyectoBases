package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, Integer> {
    Optional<Ciclo> findByIdCiclo(int idCiclo);
    boolean existsByIdCiclo(int idCiclo);

    // Reporte Simple: Historial de ciclos por paciente
    @Query("SELECT c FROM Ciclo c WHERE c.paciente.cedula = :cedula ORDER BY c.fechaInicio DESC")
    List<Ciclo> findCiclosByPaciente(@Param("cedula") Integer cedula);

    // Reporte Intermedio: Estadísticas de ciclo
    @Query("SELECT AVG(c.duracion) FROM Ciclo c WHERE c.paciente.cedula = :cedula")
    Double findPromedioDuracionByPaciente(@Param("cedula") Integer cedula);

    @Query("SELECT AVG(c.intensidad) FROM Ciclo c WHERE c.paciente.cedula = :cedula")
    Double findPromedioIntensidadByPaciente(@Param("cedula") Integer cedula);

    // Reporte Complejo: Análisis por rango de fechas
    @Query("SELECT c FROM Ciclo c WHERE c.fechaInicio BETWEEN :fechaInicio AND :fechaFin")
    List<Ciclo> findCiclosByRangoFechas(@Param("fechaInicio") LocalDate fechaInicio,
                                        @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT c.intensidad, COUNT(c) FROM Ciclo c WHERE c.fechaInicio BETWEEN :fechaInicio AND :fechaFin GROUP BY c.intensidad")
    List<Object[]> findEstadisticasIntensidadByRango(@Param("fechaInicio") LocalDate fechaInicio,
                                                     @Param("fechaFin") LocalDate fechaFin);
}