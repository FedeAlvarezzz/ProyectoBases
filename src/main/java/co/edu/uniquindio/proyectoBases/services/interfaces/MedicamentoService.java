package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoService {
    Medicamento crearMedicamento(Medicamento medicamento);

    Medicamento obtenerMedicamento(String id);

    Medicamento actualizarMedicamento(Medicamento medicamento);

    void eliminarMedicamento(String id);

    List<Medicamento> listarMedicamentos();

    List<Medicamento> obtenerMedicamentosPorIds(List<Long> medicamentoIds);
}