package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
    Medicamento findByIdMedicamento(Integer idMedicamento);


    @Query("SELECT med FROM Medicamento med ORDER BY med.nombreMedicamento")
    List<Medicamento> findAllOrdered();
}