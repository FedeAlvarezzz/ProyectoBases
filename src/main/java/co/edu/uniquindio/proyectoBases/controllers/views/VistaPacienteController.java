package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Paciente;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import co.edu.uniquindio.proyectoBases.services.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vista/pacientes")
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
            return "crear_paciente"; // Vuelve al formulario con errores
        }

        pacienteService.crearPaciente(paciente);
        model.addAttribute("mensajeExito", "Paciente creado exitosamente");
        return "crear_paciente"; // Mostrar modal en esta misma vista
    }

}

