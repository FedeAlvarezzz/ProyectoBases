package co.edu.uniquindio.proyectoBases.repositories;

import co.edu.uniquindio.proyectoBases.domain.Embarazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmbarazoRepo extends JpaRepository<Embarazo, String> {

    Embarazo findByIdEmbarazo(String idEmbarazo);

    List<Embarazo> findByPacienteCedula(Integer cedulaPaciente);
}