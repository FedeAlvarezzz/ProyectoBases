package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Medicamento;
import co.edu.uniquindio.proyectoBases.repositories.MedicamentoRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
public class VistaMedicamentoController {

    private final MedicamentoService medicamentoService;
    private final MedicamentoRepository medicamentoRepository;

    @GetMapping
    public String listarMedicamentos(Model model) {
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
        return "medicamentos/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("medicamento", new Medicamento());
        return "medicamentos/crear";
    }

    @PostMapping("/guardar")
    public String guardarMedicamento(@Valid @ModelAttribute("medicamento") Medicamento medicamento,
                                     BindingResult result,
                                     Model model,
                                     RedirectAttributes redirectAttributes){

        // Verificar si hay errores de validación
        if (result.hasErrors()) {
            // Agregar información de depuración
            System.out.println("Errores de validación encontrados:");
            result.getAllErrors().forEach(error -> {
                System.out.println("- " + error.getDefaultMessage());
            });

            // Mantener los datos del formulario y mostrar errores
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            return "medicamentos/crear";
        }

        try {
            // Agregar logs para depuración
            System.out.println("Intentando guardar medicamento: " + medicamento.getNombreMedicamento());

            medicamentoService.crearMedicamento(medicamento);

            System.out.println("Medicamento guardado exitosamente");

            // Usar RedirectAttributes para mensajes flash
            redirectAttributes.addFlashAttribute("mensaje", "Medicamento guardado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");

            return "redirect:/medicamentos";

        } catch (Exception e) {
            // Capturar cualquier excepción del servicio
            System.out.println("Error al guardar medicamento: " + e.getMessage());
            e.printStackTrace();

            model.addAttribute("error", "Error al guardar el medicamento: " + e.getMessage());
            return "medicamentos/crear";
        }
    }



    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable String id, Model model) {
        try {
            Medicamento medicamento = medicamentoService.obtenerMedicamento(id);
            model.addAttribute("medicamento", medicamento);
            return "medicamentos/editar";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/medicamentos";
        }
    }

    @PostMapping("/editar")
    public String editarMedicamento(@Valid @ModelAttribute Medicamento medicamento,
                                    BindingResult result,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "medicamentos/editar";
        }

        try {
            medicamentoService.actualizarMedicamento(medicamento);
            redirectAttributes.addFlashAttribute("mensaje", "Medicamento actualizado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            return "redirect:/medicamentos";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "medicamentos/editar";
        }
    }

    @GetMapping("/ver/{id}")
    public String verMedicamento(@PathVariable String id, Model model) {
        try {
            Medicamento medicamento = medicamentoService.obtenerMedicamento(id);
            model.addAttribute("medicamento", medicamento);
            return "medicamentos/ver";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/medicamentos";
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarMedicamento(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            medicamentoService.eliminarMedicamento(id);
            redirectAttributes.addFlashAttribute("mensaje", "Medicamento eliminado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/medicamentos";
    }

    @GetMapping("/buscar")
    public String buscarMedicamentos(@RequestParam(required = false) String nombre,
                                     @RequestParam(required = false) String principioActivo,
                                     Model model) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            model.addAttribute("medicamentos", medicamentoRepository.findByNombreMedicamento((nombre)));
            model.addAttribute("criterioBusqueda", "nombre: " + nombre);
        } else {
            model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
        }

        model.addAttribute("nombre", nombre);
        return "medicamentos/listar";
    }
}