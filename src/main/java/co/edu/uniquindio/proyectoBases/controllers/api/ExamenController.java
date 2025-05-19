package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.Examen;
import co.edu.uniquindio.proyectoBases.services.interfaces.ExamenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/examenes")
public class ExamenController {

    private final ExamenService examenService;

    @PostMapping("/crear-examen")
    public ResponseEntity<Examen> crearExamen(@Valid @RequestBody Examen examen) {
        return ResponseEntity.ok(examenService.crearExamen(examen));
    }

    @GetMapping("/obtener-examen/{idExamen}")
    public ResponseEntity<Optional<Examen>> obtenerExamen(@PathVariable String idExamen) {
        Optional<Examen> examen = examenService.obtenerExamen(idExamen);
        return ResponseEntity.ok(examen);
    }

    @PutMapping("/actualizar-examen/{idExamen}")
    public ResponseEntity<Examen> actualizarExamen(@PathVariable String idExamen, @Valid @RequestBody Examen examen) {
        examen.setIdExamen(idExamen);
        return ResponseEntity.ok(examenService.actualizarExamen(examen));
    }

    @DeleteMapping("/eliminar-examen/{idExamen}")
    public ResponseEntity<Void> eliminarExamen(@PathVariable String idExamen) {
        examenService.eliminarExamen(idExamen);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-examenes")
    public ResponseEntity<List<Examen>> listarExamenes() {
        List<Examen> examenes = examenService.listarExamenes();
        return ResponseEntity.ok(examenes);
    }
}