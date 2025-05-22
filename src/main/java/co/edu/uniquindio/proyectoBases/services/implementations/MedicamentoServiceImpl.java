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
    public Optional<Medicamento> obtenerMedicamento(Integer id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new RuntimeException("No existe un medicamento con ese ID");
        }
        return medicamentoRepository.findById(id);
    }

    @Override
    public Medicamento actualizarMedicamento(Medicamento medicamento) {
        if (!medicamentoRepository.existsById(medicamento.getIdMedicamento())) {
            throw new RuntimeException("No existe un medicamento con ese ID");
        }
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public void eliminarMedicamento(Integer id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new RuntimeException("No existe un medicamento con ese ID");
        }
        medicamentoRepository.deleteById(id);
    }

    @Override
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepository.findAll();
    }
}