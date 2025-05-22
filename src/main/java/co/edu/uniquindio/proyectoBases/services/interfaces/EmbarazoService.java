package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Embarazo;

import java.util.List;
import java.util.Optional;

public interface EmbarazoService {
    Embarazo crearEmbarazo(Embarazo embarazo);

    Optional<Embarazo> obtenerEmbarazo(String id);

    Embarazo actualizarEmbarazo(Embarazo embarazo);

    void eliminarEmbarazo(String id);

    List<Embarazo> listarEmbarazos();
}