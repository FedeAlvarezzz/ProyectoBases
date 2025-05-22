package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.Paciente;
import co.edu.uniquindio.proyectoBases.domain.enums.EstadoUsuario;
import co.edu.uniquindio.proyectoBases.repositories.PacienteRepo;
import co.edu.uniquindio.proyectoBases.services.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepo pacienteRepo;

    @Override
    public Paciente crearPaciente(Paciente paciente) {

        if (pacienteRepo.existsByCedula(paciente.getCedula())) {
            throw new RuntimeException("Ya existe un paciente con esa cédula");
        }

        if (pacienteRepo.existsByEmail(paciente.getEmail())) {
            throw new RuntimeException("Ya existe un paciente con ese email");
        }

        paciente.setEstado(EstadoUsuario.ACTIVO);
        return pacienteRepo.save(paciente);
    }

    @Override
    public Paciente obtenerPaciente(Integer cedula) {
        if (!pacienteRepo.existsByCedula(cedula)) {
            throw new RuntimeException("No existe un paciente con esa cédula");
        }

        return pacienteRepo.findByCedula(cedula);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {

        if (!pacienteRepo.existsByCedula(paciente.getCedula())) {
            throw new RuntimeException("No existe un paciente con esa cédula");
        }

        if (!pacienteRepo.existsByEmail(paciente.getEmail())){
            throw new RuntimeException("No existe un paciente con ese email");
        }

        paciente.setEstado(EstadoUsuario.ACTIVO);
        return pacienteRepo.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer cedula) {

        Paciente paciente = pacienteRepo.findByCedula(cedula);
        if (paciente == null) {
            throw new RuntimeException("No existe un paciente con esa cédula");
        }
        if (paciente.getEstado() == EstadoUsuario.ELIMINADO) {
            throw new RuntimeException("El paciente ya está eliminado");
        }
        if (!paciente.getConsultas().isEmpty()) {
            throw new RuntimeException("No se puede eliminar un paciente con consultas asociadas");
        }

        paciente.setEstado(EstadoUsuario.ELIMINADO);
        pacienteRepo.save(paciente);

    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepo.findByEstado(EstadoUsuario.ACTIVO);
    }
}
