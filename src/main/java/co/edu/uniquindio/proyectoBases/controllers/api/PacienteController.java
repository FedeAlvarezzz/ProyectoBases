package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.Paciente;
import co.edu.uniquindio.proyectoBases.services.interfaces.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping("/crear-paciente")
    public ResponseEntity<Paciente> crearPaciente(@Valid @RequestBody Paciente paciente) {

        return ResponseEntity.ok(pacienteService.crearPaciente(paciente));
    }


    @GetMapping("/obtener-paciente/{cedula}")
    public ResponseEntity<Optional<Paciente>> obtenerPaciente(@PathVariable Integer cedula) {
        Optional<Paciente> paciente = pacienteService.obtenerPaciente(cedula);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/actualizar-paciente/{cedula}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Integer cedula, @Valid @RequestBody Paciente paciente) {
        paciente.setCedula(cedula);
        return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
    }

    @DeleteMapping("/eliminar-paciente/{cedula}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Integer cedula) {
        pacienteService.eliminarPaciente(cedula);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-pacientes")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        return ResponseEntity.ok(pacientes);
    }


}
