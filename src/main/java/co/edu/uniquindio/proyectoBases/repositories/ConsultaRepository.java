package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findAllByPacienteCedula(Integer cedulaPaciente);
    List<Consulta> findAllByMedicoCedula(Integer cedulaMedico);
    @Query("SELECT c FROM Consulta c JOIN FETCH c.medico")
    List<Consulta> findAllWithMedico();
}