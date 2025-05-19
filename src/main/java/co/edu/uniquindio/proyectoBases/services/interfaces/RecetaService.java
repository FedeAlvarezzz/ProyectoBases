package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Receta;

import java.util.List;

public interface RecetaService {

    Receta crearReceta(Receta receta);
    Receta obtenerReceta(Long id);
    List<Receta> listarRecetas();
    void eliminarReceta(Long id);
    Receta actualizarReceta(Receta receta);
}
