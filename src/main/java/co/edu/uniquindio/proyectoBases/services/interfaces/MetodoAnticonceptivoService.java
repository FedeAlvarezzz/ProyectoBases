package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.MetodoAnticonceptivo;
import java.util.List;
import java.util.Optional;

public interface MetodoAnticonceptivoService {
    MetodoAnticonceptivo crearMetodo(MetodoAnticonceptivo metodo);
    Optional<MetodoAnticonceptivo> obtenerMetodo(String idMetodo);
    MetodoAnticonceptivo actualizarMetodo(MetodoAnticonceptivo metodo);
    void eliminarMetodo(String idMetodo);
    List<MetodoAnticonceptivo> listarMetodos();
}