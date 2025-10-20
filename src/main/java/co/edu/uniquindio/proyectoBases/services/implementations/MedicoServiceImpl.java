package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.Medico;
import co.edu.uniquindio.proyectoBases.domain.enums.EstadoUsuario;
import co.edu.uniquindio.proyectoBases.repositories.MedicoRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    @Override
    public Medico crearMedico(Medico medico) {


        if (medicoRepository.existsByCedula(medico.getCedula())) {
            throw new RuntimeException("Ya existe un médico con esa cédula");
        }
        if (medicoRepository.existsByEmail(medico.getEmail())) {
            throw new RuntimeException("Ya existe un médico con ese email");
        }

        medico.setEstado(EstadoUsuario.ACTIVO);
        return medicoRepository.save(medico);
    }

    @Override
    public Optional<Medico> obtenerMedico(Integer cedula) {
        if (!medicoRepository.existsByCedula(cedula)) {
            throw new RuntimeException("No existe un médico con esa cédula");
        }
        return medicoRepository.findByCedula(cedula);
    }

    @Override
    public Medico actualizarMedico(Medico medico) {
        if (!medicoRepository.existsByCedula(medico.getCedula())) {
            throw new RuntimeException("No existe un médico con esa cédula");
        }
        return medicoRepository.save(medico);
    }

    @Override
    public void eliminarMedico(Integer cedula) {
        Medico medico = medicoRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("No existe un médico con esa cédula"));

        medico.setEstado(EstadoUsuario.ELIMINADO);
        medicoRepository.save(medico);
    }

    @Override
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }
}