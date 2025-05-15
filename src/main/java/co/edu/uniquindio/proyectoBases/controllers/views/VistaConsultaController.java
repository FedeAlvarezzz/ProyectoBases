package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Consulta;
import co.edu.uniquindio.proyectoBases.services.interfaces.ConsultaService;
import co.edu.uniquindio.proyectoBases.services.interfaces.MedicoService;
import co.edu.uniquindio.proyectoBases.services.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class VistaConsultaController {

    private final ConsultaService consultaService;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    @GetMapping
    public String listarConsultas(Model model) {
        List<Consulta> consultas = consultaService.listarConsultas();
        model.addAttribute("consultas", consultas);
        return "consultas/listar";
    }

    @GetMapping("/paciente/{cedula}")
    public String listarPorPaciente(@PathVariable("cedula") Integer cedula, Model model) {
        List<Consulta> consultas = consultaService.listarConsultasPorPaciente(cedula);
        model.addAttribute("consultas", consultas);
        model.addAttribute("titulo", "Consultas del Paciente " + cedula);
        return "consultas/listar";
    }

    @GetMapping("/medico/{cedula}")
    public String listarPorMedico(@PathVariable("cedula") Integer cedula, Model model) {
        List<Consulta> consultas = consultaService.listarConsultasPorMedico(cedula);
        model.addAttribute("consultas", consultas);
        model.addAttribute("titulo", "Consultas del Médico " + cedula);
        return "consultas/listar";
    }

    @GetMapping("/consultas/filtrar")
    public String mostrarFormularioFiltrado(Model model) {
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("medicos", medicoService.listarMedicos());
        return "consulta/filtrar";
    }

    @PostMapping("/filtrar/paciente")
    public String filtrarPorPaciente(@RequestParam("cedulaPaciente") Integer cedula) {
        return "redirect:/consultas/paciente/" + cedula;
    }

    @PostMapping("/filtrar/medico")
    public String filtrarPorMedico(@RequestParam("cedulaMedico") Integer cedula) {
        return "redirect:/consultas/medico/" + cedula;
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("medicos", medicoService.listarMedicos());
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("accion", "/consultas/crear");
        return "consultas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) throws Exception {
        Consulta consulta = consultaService.obtenerConsulta(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("medicos", medicoService.listarMedicos());
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("accion", "/consultas/editar");
        return "consultas/formulario";
    }

    @PostMapping("/crear")
    public String crearConsulta(@ModelAttribute Consulta consulta) {
        consultaService.crearConsulta(consulta);
        return "redirect:/consultas";
    }

    @PostMapping("/editar")
    public String editarConsulta(@ModelAttribute Consulta consulta) throws Exception {
        consultaService.actualizarConsulta(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarConsulta(@PathVariable Long id) throws Exception {
        consultaService.eliminarConsulta(id);
        return "redirect:/consultas";
    }

    @GetMapping("/ver/{id}")
    public String verConsulta(@PathVariable Long id, Model model) throws Exception {
        Consulta consulta = consultaService.obtenerConsulta(id);
        model.addAttribute("consulta", consulta);
        return "consultas/ver";
    }

}
