package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.Embarazo;
import co.edu.uniquindio.proyectoBases.services.interfaces.EmbarazoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/embarazos")
public class EmbarazoController {

    private final EmbarazoService embarazoService;

    @PostMapping("/crear-embarazo")
    public ResponseEntity<Embarazo> crearEmbarazo(@Valid @RequestBody Embarazo embarazo) {
        return ResponseEntity.ok(embarazoService.crearEmbarazo(embarazo));
    }

    @GetMapping("/obtener-embarazo/{id}")
    public ResponseEntity<Optional<Embarazo>> obtenerEmbarazo(@PathVariable String id) {
        Optional<Embarazo> embarazo = embarazoService.obtenerEmbarazo(id);
        return ResponseEntity.ok(embarazo);
    }

    @PutMapping("/actualizar-embarazo/{id}")
    public ResponseEntity<Embarazo> actualizarEmbarazo(@PathVariable String id, @Valid @RequestBody Embarazo embarazo) {
        embarazo.setIdEmbarazo(id);
        return ResponseEntity.ok(embarazoService.actualizarEmbarazo(embarazo));
    }

    @DeleteMapping("/eliminar-embarazo/{id}")
    public ResponseEntity<Void> eliminarEmbarazo(@PathVariable String id) {
        embarazoService.eliminarEmbarazo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-embarazos")
    public ResponseEntity<List<Embarazo>> listarEmbarazos() {
        List<Embarazo> embarazos = embarazoService.listarEmbarazos();
        return ResponseEntity.ok(embarazos);
    }
}