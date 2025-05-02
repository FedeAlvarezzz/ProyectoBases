package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Paciente crearPaciente(Paciente paciente);

    Optional<Paciente> obtenerPaciente(Integer cedula);

    Paciente actualizarPaciente(Paciente paciente);

    void eliminarPaciente(Integer cedula);

    List<Paciente> listarPacientes();
}
