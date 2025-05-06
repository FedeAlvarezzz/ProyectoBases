package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Consulta;
import java.util.List;

public interface ConsultaService {
    Consulta crearConsulta(Consulta consulta);
    Consulta obtenerConsulta(Long idConsulta) throws Exception;
    Consulta actualizarConsulta(Consulta consulta) throws Exception;
    void eliminarConsulta(Long idConsulta) throws Exception;
    List<Consulta> listarConsultas();
    List<Consulta> listarConsultasPorPaciente(Integer cedulaPaciente);
    List<Consulta> listarConsultasPorMedico(Integer cedulaMedico);
}