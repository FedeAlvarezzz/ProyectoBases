package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Paciente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String listarPacientes(Model model) {


        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "pacientes/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/crear";
    }


    @PostMapping("/guardar")
    public String guardarPaciente(@ModelAttribute("paciente") @Valid Paciente paciente,
                                  BindingResult result,
                                  Model model) {

        if (result.hasErrors()) {
            return "pacientes/crear";
        }

        pacienteService.crearPaciente(paciente);
        model.addAttribute("mensajeExito", "Paciente creado exitosamente");
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{cedula}")
    public String mostrarFormularioEdicion(@PathVariable Integer cedula, Model model) {
        Optional<Paciente> pacienteOpt = pacienteService.obtenerPaciente(cedula);

        if (pacienteOpt.isPresent()) {
            model.addAttribute("paciente", pacienteOpt.get());
        }else {
            model.addAttribute("mensajeError", "Paciente no encontrado");
            return "error"; // crea una vista error.html o muestra el mensaje en la misma
        }

        return "pacientes/editar";
    }

    @PostMapping("/actualizar")
    public String actualizarPaciente(@Valid @ModelAttribute("paciente") Paciente paciente,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return "pacientes/editar";
        }
        pacienteService.actualizarPaciente(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/eliminar/{cedula}")
    public String eliminarPaciente(@PathVariable Integer cedula, Model model) {
        Optional<Paciente> pacienteOpt = pacienteService.obtenerPaciente(cedula);

        if (pacienteOpt.isPresent()) {
            pacienteService.eliminarPaciente(cedula);
            model.addAttribute("mensajeExito", "Paciente eliminado exitosamente");
        } else {
            model.addAttribute("mensajeError", "Paciente no encontrado");
        }

        return "redirect:/pacientes";
    }


    @GetMapping("/{cedula}")
    public String mostrarPerfilPaciente(@PathVariable Integer cedula, Model model) {
        Optional<Paciente> pacienteOpt = pacienteService.obtenerPaciente(cedula);

        if (pacienteOpt.isPresent()) {
            model.addAttribute("paciente", pacienteOpt.get());
        } else {
            model.addAttribute("mensajeError", "Paciente no encontrado");
            return "error"; // crea una vista error.html o muestra el mensaje en la misma
        }

        return "pacientes/perfil";
    }



}
