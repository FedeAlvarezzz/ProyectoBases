package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {
    Medico findByCedula(Integer cedula);
    boolean existsByCedula(Integer cedula);
    boolean existsByEmail(String email);

    // En MedicoRepository
    @Query("SELECT m FROM Medico m ORDER BY m.apellido")
    List<Medico> findAllMedicos();



// lista por Medicos con Datos de Consulta
    /*
    @Query("SELECT m FROM Medico m WHERE m.cedula IN " +
            "(SELECT c.medico.cedula FROM Consulta c GROUP BY c.medico.cedula " +
            "HAVING COUNT(c) > (SELECT AVG(cnt) FROM (SELECT COUNT(c2) as cnt FROM Consulta c2 GROUP BY c2.medico) as subq))")
    List<Medico> findMedicosConMasConsultasQueElPromedio();

     */
}