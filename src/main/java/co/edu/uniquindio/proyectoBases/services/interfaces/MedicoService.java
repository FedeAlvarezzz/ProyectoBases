package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Medico;
import java.util.List;
import java.util.Optional;

public interface MedicoService {
    Medico crearMedico(Medico medico);
    Optional<Medico> obtenerMedico(Integer cedula);
    Medico actualizarMedico(Medico medico);
    void eliminarMedico(Integer cedula);
    List<Medico> listarMedicos();
}