package co.edu.uniquindio.proyectoBases.controllers;

import co.edu.uniquindio.proyectoBases.domain.Medico;
import co.edu.uniquindio.proyectoBases.services.interfaces.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> crearMedico(@Valid @RequestBody Medico medico) {
        return ResponseEntity.ok(medicoService.crearMedico(medico));
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<Optional<Medico>> obtenerMedico(@PathVariable String cedula) {
        Optional<Medico> medico = medicoService.obtenerMedico(cedula);
        return ResponseEntity.ok(medico);
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable String cedula, @Valid @RequestBody Medico medico) {
        medico.setCedula(cedula);
        return ResponseEntity.ok(medicoService.actualizarMedico(medico));
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable String cedula) {
        medicoService.eliminarMedico(cedula);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() {
        List<Medico> medicos = medicoService.listarMedicos();
        return ResponseEntity.ok(medicos);
    }
}