package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findAllByPacienteCedula(Integer cedulaPaciente);
    List<Consulta> findAllByMedicoCedula(Integer cedulaMedico);
   //lista por consultas recientes
   @Query("SELECT c FROM Consulta c ORDER BY c.fecha DESC, c.hora DESC")
   List<Consulta> findConsultasRecientes();
//lista por Consultas por Médico con Datos del Paciente
    @Query("SELECT c FROM Consulta c JOIN FETCH c.medico m JOIN FETCH c.paciente p ORDER BY m.apellido, c.fecha DESC")
    List<Consulta> findAllWithMedicoAndPaciente();
// lista por Estadísticas de Consultas por Especialidad
    @Query("SELECT m.especialidad, COUNT(c) as cantidad, AVG(c.valor) as promedioValor " +
            "FROM Consulta c JOIN c.medico m " +
            "GROUP BY m.especialidad " +
            "ORDER BY cantidad DESC")
    List<Object[]> findEstadisticasPorEspecialidad();
}