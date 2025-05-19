package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.Consulta;
import co.edu.uniquindio.proyectoBases.services.interfaces.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> crearConsulta(@Valid @RequestBody Consulta consulta) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(consultaService.crearConsulta(consulta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> obtenerConsulta(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(consultaService.obtenerConsulta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizarConsulta(
            @PathVariable Long id,
            @Valid @RequestBody Consulta consulta) throws Exception {
        consulta.setIdConsulta(id);
        return ResponseEntity.ok(consultaService.actualizarConsulta(consulta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable Long id) throws Exception {
        consultaService.eliminarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodasLasConsultas() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @GetMapping("/paciente/{cedulaPaciente}")
    public ResponseEntity<List<Consulta>> listarConsultasPorPaciente(
            @PathVariable Integer cedulaPaciente) {
        return ResponseEntity.ok(consultaService.listarConsultasPorPaciente(cedulaPaciente));
    }

    @GetMapping("/medico/{cedulaMedico}")
    public ResponseEntity<List<Consulta>> listarConsultasPorMedico(
            @PathVariable Integer cedulaMedico) {
        return ResponseEntity.ok(consultaService.listarConsultasPorMedico(cedulaMedico));
    }
}