package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.HistoriaClinica;
import co.edu.uniquindio.proyectoBases.services.interfaces.HistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/historiaClinica")
@RestController
@RequiredArgsConstructor
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    @PostMapping
    public ResponseEntity<HistoriaClinica> crearHistoria(@RequestBody HistoriaClinica historiaClinica) {
        return ResponseEntity.ok(historiaClinicaService.crearHistoriaClinica(historiaClinica));

    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> obtenerHistoria(@PathVariable Long id) {
        return ResponseEntity.ok(historiaClinicaService.obtenerHistoriaClinica(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> actualizarHistoria(@PathVariable Long id, @RequestBody HistoriaClinica historiaClinica) {
        return ResponseEntity.ok(historiaClinicaService.actualizarHistoriaClinica(historiaClinica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistoria(@PathVariable Long id)  {
        historiaClinicaService.eliminarHistoriaClinica(id);
        return ResponseEntity.noContent().build();
    }


}
