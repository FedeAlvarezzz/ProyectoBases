package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.Consulta;
import co.edu.uniquindio.proyectoBases.repositories.ConsultaRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Override
    public Consulta crearConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public Consulta obtenerConsulta(Long idConsulta) throws Exception {
        return consultaRepository.findById(idConsulta)
                .orElseThrow(() -> new Exception("No se encontró la consulta con ID: " + idConsulta));
    }

    @Override
    public Consulta actualizarConsulta(Consulta consulta) throws Exception {
        if(!consultaRepository.existsById(consulta.getIdConsulta())) {
            throw new Exception("No existe una consulta con el ID: " + consulta.getIdConsulta());
        }
        return consultaRepository.save(consulta);
    }

    @Override
    public void eliminarConsulta(Long idConsulta) throws Exception {
        if(!consultaRepository.existsById(idConsulta)) {
            throw new Exception("No existe una consulta con el ID: " + idConsulta);
        }
        consultaRepository.deleteById(idConsulta);
    }

    @Override
    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    @Override
    public List<Consulta> listarConsultasPorPaciente(Integer cedulaPaciente) {
        return consultaRepository.findAllByPacienteCedula(cedulaPaciente);
    }

    @Override
    public List<Consulta> listarConsultasPorMedico(Integer cedulaMedico) {
        return consultaRepository.findAllByMedicoCedula(cedulaMedico);
    }
}