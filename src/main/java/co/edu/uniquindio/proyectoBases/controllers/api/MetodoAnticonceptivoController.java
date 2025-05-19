package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.MetodoAnticonceptivo;
import co.edu.uniquindio.proyectoBases.services.interfaces.MetodoAnticonceptivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/metodos-anticonceptivos")
public class MetodoAnticonceptivoController {

    private final MetodoAnticonceptivoService metodoService;

    @PostMapping("/crear")
    public ResponseEntity<MetodoAnticonceptivo> crearMetodo(@Valid @RequestBody MetodoAnticonceptivo metodo) {
        return ResponseEntity.ok(metodoService.crearMetodo(metodo));
    }

    @GetMapping("/obtener/{idMetodo}")
    public ResponseEntity<Optional<MetodoAnticonceptivo>> obtenerMetodo(@PathVariable String idMetodo) {
        Optional<MetodoAnticonceptivo> metodo = metodoService.obtenerMetodo(idMetodo);
        return ResponseEntity.ok(metodo);
    }

    @PutMapping("/actualizar/{idMetodo}")
    public ResponseEntity<MetodoAnticonceptivo> actualizarMetodo(
            @PathVariable String idMetodo,
            @Valid @RequestBody MetodoAnticonceptivo metodo) {
        metodo.setIdMetodoAnticonceptivo(idMetodo);
        return ResponseEntity.ok(metodoService.actualizarMetodo(metodo));
    }

    @DeleteMapping("/eliminar/{idMetodo}")
    public ResponseEntity<Void> eliminarMetodo(@PathVariable String idMetodo) {
        metodoService.eliminarMetodo(idMetodo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MetodoAnticonceptivo>> listarMetodos() {
        List<MetodoAnticonceptivo> metodos = metodoService.listarMetodos();
        return ResponseEntity.ok(metodos);
    }
}