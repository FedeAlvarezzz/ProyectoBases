package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Examen;
import co.edu.uniquindio.proyectoBases.services.interfaces.ExamenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/examenes")
@RequiredArgsConstructor
public class VistaExamenController {

    private final ExamenService examenService;

    @GetMapping
    public String listarExamenes(Model model) {
         List<Examen> examenes = examenService.listarExamenes();
         model.addAttribute("examenes", examenes);
         return "examenes/listar";
    }

    @GetMapping("/crear")
    public String crearExamen(Model model) {
        Examen examen = new Examen();
        model.addAttribute("examen", examen);
        return "examenes/crear";
    }

    @PostMapping("/guardar")
    public String guardarExamen(Examen examen) {
        examenService.crearExamen(examen);
        return "redirect:/examenes";
    }

    @GetMapping("/editar")
    public String editarExamen(Long id, Model model) {
        Examen examen = examenService.obtenerExamen(id);
        if (examen == null) {
            return "redirect:/examenes";
        }
        model.addAttribute("examen", examen);
        return "examenes/editar";
    }

    @GetMapping("/eliminar")
    public String eliminarExamen(Long id, Model model) {
        Examen examen = examenService.obtenerExamen(id);
        if (examen == null) {
            return "redirect:/examenes";
        }
        model.addAttribute("examen", examen);
        return "examenes/eliminar";
    }

}
