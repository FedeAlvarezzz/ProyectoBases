package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.HistoriaClinica;
import co.edu.uniquindio.proyectoBases.domain.Paciente;
import co.edu.uniquindio.proyectoBases.services.interfaces.HistoriaClinicaService;
import co.edu.uniquindio.proyectoBases.services.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/historias")
@RequiredArgsConstructor
public class VistaHistoriaController {
    private final HistoriaClinicaService historiaClinicaService;
    private final PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("historias", historiaClinicaService.listarHistoriasClinicas());
        return "historias/listar";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {



        model.addAttribute("listaPacientes", pacienteService.listarPacientes());

        model.addAttribute("historiaClinica", new HistoriaClinica());
        return "historias/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute HistoriaClinica historiaClinica,
                          Integer cedulaPaciente, Model model) {

        if (cedulaPaciente == null) {
            model.addAttribute("mensajeError", "Debe seleccionar un paciente");
            return "error";
        }

        Optional<Paciente> optionalPaciente = pacienteService.obtenerPaciente(cedulaPaciente);

        if (optionalPaciente.isPresent()) {
            historiaClinica.setPaciente(optionalPaciente.get());
            historiaClinicaService.crearHistoriaClinica(historiaClinica);
            return "redirect:/historias";
        } else {
            model.addAttribute("mensajeError", "Paciente no encontrado");
            return "error";
        }
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) throws Exception {
        HistoriaClinica historia = historiaClinicaService.obtenerHistoriaClinica(id);
        model.addAttribute("historiaClinica", historia);
        return "historias/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) throws Exception {
        historiaClinicaService.eliminarHistoriaClinica(id);
        return "redirect:/historias";
    }

    @GetMapping("/ver/{id}")
    public String verHistoria(@PathVariable Long id, Model model) throws Exception {
        HistoriaClinica historia = historiaClinicaService.obtenerHistoriaClinica(id);
        model.addAttribute("historiaClinica", historia);
        return "historias/ver";
    }


}
