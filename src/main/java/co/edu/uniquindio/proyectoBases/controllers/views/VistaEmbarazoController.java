package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Embarazo;
import co.edu.uniquindio.proyectoBases.domain.Paciente;
import co.edu.uniquindio.proyectoBases.services.interfaces.EmbarazoService;
import co.edu.uniquindio.proyectoBases.services.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/embarazos")
@RequiredArgsConstructor
public class VistaEmbarazoController {

    private final EmbarazoService embarazoService;
    private final PacienteService pacienteService;

    // ------------------- LISTAR -------------------

    /**
     * Lista todos los embarazos y pacientes.
     * Vista: embarazos/listar.html
     */
    @GetMapping
    public String listarEmbarazos(Model model) {
        List<Embarazo> embarazos = embarazoService.listarEmbarazos();
        List<Paciente> pacientes = pacienteService.listarPacientes();
        model.addAttribute("embarazos", embarazos);
        model.addAttribute("listaPacientes", pacientes);
        return "embarazos/seleccionar";
    }

    /**
     * Lista los embarazos de un paciente específico.
     * Vista: embarazos/listar.html
     */
    @GetMapping("/paciente/{cedula}")
    public String listarPorPaciente(@PathVariable("cedula") Integer cedula, Model model) {
        List<Embarazo> embarazos = embarazoService.listarPorPaciente(cedula);
        Paciente paciente = pacienteService.obtenerPaciente(cedula);
        model.addAttribute("embarazos", embarazos);
        model.addAttribute("paciente", paciente);
        return "embarazos/listar";
    }

    // ------------------- CREAR -------------------

    /**
     * Muestra el formulario para registrar un nuevo embarazo.
     * Vista: embarazos/registrar.html
     */
    @GetMapping("/nuevo/{cedula}")
    public String mostrarFormularioCreacion(@PathVariable("cedula") Integer cedula, Model model) {
        Embarazo embarazo = new Embarazo();
        embarazo.setFechaConcepcion(LocalDate.now());
        embarazo.setFechaPartoEsperado(LocalDate.now().plusWeeks(40));
        model.addAttribute("embarazo", embarazo);
        model.addAttribute("cedulaPaciente", cedula);
        return "embarazos/registrar";
    }

    /**
     * Procesa el registro de un nuevo embarazo.
     */
    @PostMapping("/crear")
    public String crearEmbarazo(@ModelAttribute Embarazo embarazo,
                                @RequestParam("cedulaPaciente") Integer cedula) {
        Paciente paciente = pacienteService.obtenerPaciente(cedula);
        embarazo.setPaciente(paciente);
        embarazoService.crearEmbarazo(embarazo);
        return "redirect:/embarazos/paciente/" + cedula;
    }

    // ------------------- EDITAR -------------------

    /**
     * Muestra el formulario para editar un embarazo existente.
     * Vista: embarazos/editar.html
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") String id, Model model) throws Exception {
        Embarazo embarazo = embarazoService.obtenerEmbarazo(id);
        model.addAttribute("embarazo", embarazo);
        model.addAttribute("cedulaPaciente", embarazo.getPaciente().getCedula());
        // Se recomienda pasar la lista de tipos de embarazo si es necesario para el select
        // model.addAttribute("tiposEmbarazo", embarazoService.listarTiposEmbarazo());
        return "embarazos/editar";
    }

    /**
     * Procesa la edición de un embarazo.
     */
    @PostMapping("/actualizar")
    public String actualizarEmbarazo(@ModelAttribute Embarazo embarazo) throws Exception {
        embarazoService.actualizarEmbarazo(embarazo);
        return "redirect:/embarazos/paciente/" + embarazo.getPaciente().getCedula();
    }

    // ------------------- SEGUIMIENTO -------------------

    /**
     * Muestra la vista de seguimiento de un embarazo.
     * Vista: embarazos/seguimiento.html
     */
    @GetMapping("/seguimiento/{id}")
    public String verSeguimiento(@PathVariable("id") String id, Model model) throws Exception {
        Embarazo embarazo = embarazoService.obtenerEmbarazo(id);
        long semanasTranscurridas = ChronoUnit.WEEKS.between(embarazo.getFechaConcepcion(), LocalDate.now());
        model.addAttribute("embarazo", embarazo);
        model.addAttribute("semanasTranscurridas", semanasTranscurridas);
        return "embarazos/seguimiento";
    }

    // ------------------- CONTROLES -------------------

    /**
     * Muestra la vista de controles prenatales de un embarazo.
     * Vista: embarazos/controles.html
     */
    @GetMapping("/controles/{id}")
    public String verControles(@PathVariable("id") String id, Model model) throws Exception {
        Embarazo embarazo = embarazoService.obtenerEmbarazo(id);
        long semanasTranscurridas = ChronoUnit.WEEKS.between(embarazo.getFechaConcepcion(), LocalDate.now());
        model.addAttribute("embarazo", embarazo);
        model.addAttribute("semanasTranscurridas", semanasTranscurridas);
        // Aquí podrías agregar la lista de controles si la tienes
        // model.addAttribute("controles", controlService.listarPorEmbarazo(id));
        return "embarazos/controles";
    }

}
