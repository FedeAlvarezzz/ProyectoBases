package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Paciente;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import co.edu.uniquindio.proyectoBases.services.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class VistaPacienteController {

    private final PacienteService pacienteService;


    @GetMapping("/crear")
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "crear_paciente";
    }

    @PostMapping("/crear")
    public String guardarPaciente(@ModelAttribute("paciente") @Valid Paciente paciente,
                                  BindingResult result,
                                  Model model) {

        if (result.hasErrors()) {
            return "crear_paciente";
        }

        pacienteService.crearPaciente(paciente);
        model.addAttribute("mensajeExito", "Paciente creado exitosamente");
        return "crear_paciente";
    }

    @GetMapping("/perfil")
    public String mostrarPerfilPaciente(@RequestParam("cedula") Integer cedula, Model model) {
        Optional<Paciente> pacienteOpt = pacienteService.obtenerPaciente(cedula);

        if (pacienteOpt.isPresent()) {
            model.addAttribute("paciente", pacienteOpt.get());
        } else {
            model.addAttribute("mensajeError", "Paciente no encontrado");
            return "error"; // crea una vista error.html o muestra el mensaje en la misma
        }

        return "paciente";
    }



}
