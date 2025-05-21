package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoService {
    Medicamento crearMedicamento(Medicamento medicamento);

    Optional<Medicamento> obtenerMedicamento(Integer id);

    Medicamento actualizarMedicamento(Medicamento medicamento);

    void eliminarMedicamento(Integer id);

    List<Medicamento> listarMedicamentos();
}