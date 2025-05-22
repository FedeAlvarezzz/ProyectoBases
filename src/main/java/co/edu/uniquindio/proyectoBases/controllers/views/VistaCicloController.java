package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Ciclo;
import co.edu.uniquindio.proyectoBases.services.interfaces.CicloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ciclos")
@RequiredArgsConstructor
public class VistaCicloController {

    private final CicloService cicloService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ciclos", cicloService.listarCiclos());
        return "ciclos/listar";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("ciclo", new Ciclo());
        return "ciclos/crear";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Ciclo ciclo) {
        cicloService.crearCiclo(ciclo);
        return "redirect:/ciclos";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Ciclo ciclo = cicloService.obtenerCiclo(id);
        model.addAttribute("ciclo", ciclo);
        return "ciclos/editar";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Ciclo ciclo) {
        cicloService.actualizarCiclo(ciclo);
        return "redirect:/ciclos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        cicloService.eliminarCiclo(id);
        return "redirect:/ciclos";
    }

}