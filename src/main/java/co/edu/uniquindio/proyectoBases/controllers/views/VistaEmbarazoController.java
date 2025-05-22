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

    @GetMapping("/paciente/{cedulaPaciente}")
    public String listarEmbarazosPorPaciente(@PathVariable Integer cedulaPaciente, Model model) {
        //model.addAttribute("embarazos", embarazoService.listarPorPaciente(cedulaPaciente));
        model.addAttribute("paciente", pacienteService.obtenerPaciente(cedulaPaciente));
        return "embarazos/listar";
    }

    @GetMapping("/nuevo/{cedulaPaciente}")
    public String mostrarFormularioRegistro(@PathVariable Integer cedulaPaciente, Model model) {
        Embarazo embarazo = new Embarazo();
        embarazo.setFechaConcepcion(LocalDate.now());
        embarazo.setFechaPartoEsperado(LocalDate.now().plusWeeks(40));

        model.addAttribute("embarazo", embarazo);
        model.addAttribute("cedulaPaciente", cedulaPaciente);
        return "embarazos/registrar";
    }

    @PostMapping("/guardar")
    public String guardarEmbarazo(@ModelAttribute Embarazo embarazo,
                                  @RequestParam Integer cedulaPaciente) {
        Paciente paciente = pacienteService.obtenerPaciente(cedulaPaciente);
        embarazo.setPaciente(paciente);
        embarazoService.crearEmbarazo(embarazo);
        return "redirect:/embarazos/paciente/" + cedulaPaciente;
    }

    @GetMapping("/editar/{idEmbarazo}")
    public String mostrarFormularioEdicion(@PathVariable String idEmbarazo, Model model) throws Exception {
        Embarazo embarazo = embarazoService.obtenerEmbarazo(idEmbarazo);
        model.addAttribute("embarazo", embarazo);
        return "embarazos/editar";
    }

    @PostMapping("/actualizar")
    public String actualizarEmbarazo(@ModelAttribute Embarazo embarazo) throws Exception {
        embarazoService.actualizarEmbarazo(embarazo);
        return "redirect:/embarazos/paciente/" + embarazo.getPaciente().getCedula();
    }

    @GetMapping("/seguimiento/{idEmbarazo}")
    public String verSeguimiento(@PathVariable String idEmbarazo, Model model) throws Exception {
        Embarazo embarazo = embarazoService.obtenerEmbarazo(idEmbarazo);
        model.addAttribute("embarazo", embarazo);
        model.addAttribute("semanasTranscurridas",
                ChronoUnit.WEEKS.between(embarazo.getFechaConcepcion(), LocalDate.now()));
        return "embarazos/seguimiento";
    }

    @GetMapping("/controles/{idEmbarazo}")
    public String verControles(@PathVariable String idEmbarazo, Model model) {
        model.addAttribute("embarazo", embarazoService.obtenerEmbarazo(idEmbarazo));
        // Aquí podrías agregar también la lista de controles prenatales si los tienes
        return "embarazos/controles";
    }
}
