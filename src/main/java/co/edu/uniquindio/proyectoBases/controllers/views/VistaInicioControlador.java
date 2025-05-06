package co.edu.uniquindio.proyectoBases.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaInicioControlador {

    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "inicio";
    }

    @GetMapping("/register")
    public String mostrarRegistro() {
        return "registro"; // asegúrate de crear esta vista
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // asegúrate de crear esta vista
    }

    @GetMapping("/eliminar-cuenta")
    public String eliminarCuenta() {
        return "eliminar_cuenta"; // crea esta vista
    }

    @GetMapping("/actualizar-perfil")
    public String actualizarPerfil() {
        return "actualizar_perfil"; // crea esta vista
    }
}
