package co.edu.uniquindio.proyectoBases.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaInicioController {

    @GetMapping("/")
    public String mostrarInicio() {
        return "inicio";
    }

}
