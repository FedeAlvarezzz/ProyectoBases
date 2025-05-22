package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Embarazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmbarazoRepo extends JpaRepository<Embarazo, String> {
    // Reporte Simple: Embarazos activos
    @Query("SELECT e FROM Embarazo e WHERE e.fechaPartoEsperado >= CURRENT_DATE ORDER BY e.fechaPartoEsperado")
    List<Embarazo> findEmbarazosActivos();

    // Reporte Intermedio: Embarazos por trimestre
    @Query("SELECT e FROM Embarazo e WHERE e.semanasGestacion BETWEEN :semanasMin AND :semanasMax")
    List<Embarazo> findEmbarazosByTrimestre(@Param("semanasMin") Integer semanasMin,
                                            @Param("semanasMax") Integer semanasMax);

    // Reporte Complejo: Estadísticas generales
    @Query("SELECT te.nombre, COUNT(e) FROM Embarazo e JOIN e.tipoEmbarazo te GROUP BY te.nombre")
    List<Object[]> findEstadisticasPorTipoEmbarazo();
}