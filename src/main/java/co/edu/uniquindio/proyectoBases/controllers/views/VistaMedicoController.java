package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Medico;
import co.edu.uniquindio.proyectoBases.services.interfaces.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class VistaMedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoService.listarMedicos());
        return "medicos/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("medico", new Medico());
        return "medicos/crear";
    }

    @PostMapping("/guardar")
    public String guardarMedico(@ModelAttribute("medico") @Valid Medico medico,
                                Model model, BindingResult result) {

        if (result.hasErrors()) {
            return "medicos/crear";
        }

        medicoService.crearMedico(medico);
        model.addAttribute("mensajeExito", "Médico creado exitosamente");
        return "redirect:/medicos";
    }

    @GetMapping("/editar/{cedula}")
    public String mostrarFormularioEdicion(@PathVariable Integer cedula, Model model) {
        Optional<Medico> medicoOpt = medicoService.obtenerMedico(cedula);

        if (medicoOpt.isPresent()) {
            model.addAttribute("medico", medicoOpt.get());
        } else {
            model.addAttribute("mensajeError", "Médico no encontrado");
            return "error"; // crea una vista error.html o muestra el mensaje en la misma
        }
        return "medicos/editar";
    }

    @PostMapping("/actualizar")
    public String actualizarMedico(@Valid @ModelAttribute("medico") Medico medico,
                                   BindingResult result,
                                   Model model) {

        if (result.hasErrors()) {
            return "medicos/editar";
        }

        medicoService.actualizarMedico(medico);
        model.addAttribute("mensajeExito", "Médico actualizado exitosamente");
        return "redirect:/medicos";
    }

    @GetMapping("/eliminar/{cedula}")
    public String eliminarMedico(@PathVariable Integer cedula, Model model) {
        Optional<Medico> medicoOpt = medicoService.obtenerMedico(cedula);

        if (medicoOpt.isPresent()) {
            medicoService.eliminarMedico(cedula);
            model.addAttribute("mensajeExito", "Médico eliminado exitosamente");
        } else {
            model.addAttribute("mensajeError", "Médico no encontrado");
        }
        return "redirect:/medicos";
    }

    @GetMapping("/{cedula}")
    public String mostrarPerfil(@PathVariable Integer cedula, Model model) {
        Optional<Medico> medicoOpt = medicoService.obtenerMedico(cedula);

        if (medicoOpt.isPresent()) {
            model.addAttribute("medico", medicoOpt.get());
        } else {
            model.addAttribute("mensajeError", "Médico no encontrado");
            return "error"; // crea una vista error.html o muestra el mensaje en la misma
        }
        return "medicos/perfil";
    }






}
