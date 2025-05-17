package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.Ciclo;
import co.edu.uniquindio.proyectoBases.services.interfaces.CicloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ciclos")
public class CicloController {

    private final CicloService cicloService;

    @PostMapping("/crear-ciclo")
    public ResponseEntity<Ciclo> crearCiclo(@Valid @RequestBody Ciclo ciclo) {
        return ResponseEntity.ok(cicloService.crearCiclo(ciclo));
    }

    @GetMapping("/obtener-ciclo/{idCiclo}")
    public ResponseEntity<Optional<Ciclo>> obtenerCiclo(@PathVariable int idCiclo) {
        Optional<Ciclo> ciclo = cicloService.obtenerCiclo(idCiclo);
        return ResponseEntity.ok(ciclo);
    }

    @PutMapping("/actualizar-ciclo/{idCiclo}")
    public ResponseEntity<Ciclo> actualizarCiclo(@PathVariable int idCiclo, @Valid @RequestBody Ciclo ciclo) {
        ciclo.setIdCiclo(idCiclo);
        return ResponseEntity.ok(cicloService.actualizarCiclo(ciclo));
    }

    @DeleteMapping("/eliminar-ciclo/{idCiclo}")
    public ResponseEntity<Void> eliminarCiclo(@PathVariable int idCiclo) {
        cicloService.eliminarCiclo(idCiclo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-ciclos")
    public ResponseEntity<List<Ciclo>> listarCiclos() {
        List<Ciclo> ciclos = cicloService.listarCiclos();
        return ResponseEntity.ok(ciclos);
    }
}