package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.Medicamento;
import co.edu.uniquindio.proyectoBases.services.interfaces.MedicamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @PostMapping("/crear-medicamento")
    public ResponseEntity<Medicamento> crearMedicamento(@Valid @RequestBody Medicamento medicamento) {
        return ResponseEntity.ok(medicamentoService.crearMedicamento(medicamento));
    }

    @GetMapping("/obtener-medicamento/{id}")
    public ResponseEntity<Optional<Medicamento>> obtenerMedicamento(@PathVariable Integer id) {
        Optional<Medicamento> medicamento = medicamentoService.obtenerMedicamento(id);
        return ResponseEntity.ok(medicamento);
    }

    @PutMapping("/actualizar-medicamento/{id}")
    public ResponseEntity<Medicamento> actualizarMedicamento(@PathVariable Integer id, @Valid @RequestBody Medicamento medicamento) {
        medicamento.setIdMedicamento(id);
        return ResponseEntity.ok(medicamentoService.actualizarMedicamento(medicamento));
    }

    @DeleteMapping("/eliminar-medicamento/{id}")
    public ResponseEntity<Void> eliminarMedicamento(@PathVariable Integer id) {
        medicamentoService.eliminarMedicamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-medicamentos")
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoService.listarMedicamentos();
        return ResponseEntity.ok(medicamentos);
    }
}