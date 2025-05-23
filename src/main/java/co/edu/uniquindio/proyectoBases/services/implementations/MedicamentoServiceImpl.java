package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.Medicamento;
import co.edu.uniquindio.proyectoBases.repositories.MedicamentoRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    @Override
    public Medicamento crearMedicamento(Medicamento medicamento) {
        if (medicamentoRepository.existsById(medicamento.getIdMedicamento())) {
            throw new RuntimeException("Ya existe un medicamento con ese ID");
        }
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public Medicamento obtenerMedicamento(String id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new RuntimeException("No existe un medicamento con ese ID");
        }
        return medicamentoRepository.findByIdMedicamento(id);
    }

    @Override
    public Medicamento actualizarMedicamento(Medicamento medicamento) {
        if (!medicamentoRepository.existsById(medicamento.getIdMedicamento())) {
            throw new RuntimeException("No existe un medicamento con ese ID");
        }
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public void eliminarMedicamento(String id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new RuntimeException("No existe un medicamento con ese ID");
        }
        medicamentoRepository.deleteById(id);
    }

    @Override
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public List<Medicamento> obtenerMedicamentosPorIds(List<Long> medicamentoIds) {

        return medicamentoRepository.findAllByIdMedicamento(medicamentoIds.toString());
    }
}