package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Examen;
import java.util.List;
import java.util.Optional;

public interface ExamenService {
    Examen crearExamen(Examen examen);
    Optional<Examen> obtenerExamen(String idExamen);
    Examen actualizarExamen(Examen examen);
    void eliminarExamen(String idExamen);
    List<Examen> listarExamenes();
}