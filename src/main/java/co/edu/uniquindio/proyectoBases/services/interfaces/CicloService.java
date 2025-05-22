package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Ciclo;
import java.util.List;
import java.util.Optional;

public interface CicloService {
    Ciclo crearCiclo(Ciclo ciclo);
    Ciclo obtenerCiclo(Integer idCiclo);
    Ciclo actualizarCiclo(Ciclo ciclo);
    void eliminarCiclo(Integer idCiclo);
    List<Ciclo> listarCiclos();
}