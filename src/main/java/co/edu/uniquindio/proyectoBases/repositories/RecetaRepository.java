package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository <Receta, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, buscar recetas por paciente, etc.
    Receta findByIdReceta(Long idReceta);

}