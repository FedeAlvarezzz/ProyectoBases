package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RecetaRepository extends JpaRepository <Receta, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, buscar recetas por paciente, etc.
    Receta findByIdReceta(Long idReceta);
    @Query("SELECT new map(r.idReceta as idReceta, r.fechaEmision as fechaEmision, r.fechaVencimiento as fechaVencimiento, " +
            "COUNT(m) as cantidadMedicamentos) " +
            "FROM Receta r LEFT JOIN r.medicamentos m GROUP BY r.idReceta, r.fechaEmision, r.fechaVencimiento")
    List<Map<String, Object>> findRecetasWithMedicamentos();

}