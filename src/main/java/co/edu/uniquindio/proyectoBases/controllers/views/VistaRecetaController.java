package co.edu.uniquindio.proyectoBases.controllers.views;

import co.edu.uniquindio.proyectoBases.domain.Receta;
import co.edu.uniquindio.proyectoBases.domain.Paciente;
import co.edu.uniquindio.proyectoBases.domain.Medicamento;
import co.edu.uniquindio.proyectoBases.services.interfaces.RecetaService;
import co.edu.uniquindio.proyectoBases.services.interfaces.PacienteService;
import co.edu.uniquindio.proyectoBases.services.interfaces.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recetas")
@RequiredArgsConstructor
public class VistaRecetaController {

    private final RecetaService recetaService;
    private final PacienteService pacienteService;
    private final MedicamentoService medicamentoService;

    @GetMapping
    public String mostrarFormularioGenerar(Model model) {
        model.addAttribute("receta", new Receta());
        model.addAttribute("listaPacientes", pacienteService.listarPacientes());
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
        return "recetas/generar";
    }

    /**
     * Procesar creación de nueva receta
     */
    @PostMapping("/guardar")
    public String guardarReceta(@Valid @ModelAttribute("receta") Receta receta,
                                BindingResult result,
                                @RequestParam(value = "medicamentoIds", required = false) List<Long> medicamentoIds,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        try {
            // Validar fechas
            if (receta.getFechaVencimiento().isBefore(receta.getFechaEmision())) {
                result.rejectValue("fechaVencimiento", "error.receta",
                        "La fecha de vencimiento debe ser posterior a la fecha de emisión");
            }

            // Validar que la fecha de vencimiento no sea en el pasado
            if (receta.getFechaVencimiento().isBefore(LocalDate.now())) {
                result.rejectValue("fechaVencimiento", "error.receta",
                        "La fecha de vencimiento no puede ser en el pasado");
            }

            if (result.hasErrors()) {
                model.addAttribute("pacientes", pacienteService.listarPacientes());
                model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
                return "recetas/generar";
            }

            // Asignar medicamentos si se proporcionaron
            if (medicamentoIds != null && !medicamentoIds.isEmpty()) {
                List<Medicamento> medicamentos = medicamentoService.obtenerMedicamentosPorIds(medicamentoIds);
                receta.setMedicamentos(medicamentos);
            }

            // Guardar la receta
            Receta recetaGuardada = recetaService.crearReceta(receta);

            redirectAttributes.addFlashAttribute("mensaje", "Receta creada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");

            return "redirect:/recetas/detalle/" + recetaGuardada.getIdReceta();

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al crear la receta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/recetas/generar";
        }
    }

    /**
     * Vista del historial de recetas
     */
    @GetMapping("/historial")
    public String mostrarHistorial(Model model,
                                   @RequestParam(value = "pacienteId", required = false) Long pacienteId,
                                   @RequestParam(value = "fechaInicio", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                   @RequestParam(value = "fechaFin", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<Receta> recetas;

        recetas = recetaService.listarRecetas();


        model.addAttribute("recetas", recetas);
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("pacienteSeleccionado", pacienteId);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);

        return "recetas/historial";
    }

    /**
     * Vista de detalle de una receta específica
     */
    @GetMapping("/detalle/{id}")
    public String mostrarDetalle(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Receta receta = recetaService.obtenerReceta(id);
            if (receta == null) {
                redirectAttributes.addFlashAttribute("mensaje", "Receta no encontrada");
                redirectAttributes.addFlashAttribute("tipo", "error");
                return "redirect:/recetas/historial";
            }

            model.addAttribute("receta", receta);
            model.addAttribute("estaVigente", receta.getFechaVencimiento().isAfter(LocalDate.now()));

            return "recetas/detalle";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al obtener la receta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/recetas/historial";
        }
    }

    /**
     * Vista optimizada para impresión
     */
    @GetMapping("/imprimir/{id}")
    public String mostrarVistaImpresion(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Receta receta = recetaService.obtenerReceta(id);
            if (receta == null) {
                redirectAttributes.addFlashAttribute("mensaje", "Receta no encontrada");
                redirectAttributes.addFlashAttribute("tipo", "error");
                return "redirect:/recetas/historial";
            }

            model.addAttribute("receta", receta);
            return "recetas/imprimir";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al obtener la receta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/recetas/historial";
        }
    }

    /**
     * Editar receta existente
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Receta receta = recetaService.obtenerReceta(id);
            if (receta == null) {
                redirectAttributes.addFlashAttribute("mensaje", "Receta no encontrada");
                redirectAttributes.addFlashAttribute("tipo", "error");
                return "redirect:/recetas/historial";
            }

            model.addAttribute("receta", receta);
            model.addAttribute("pacientes", pacienteService.listarPacientes());
            model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
            model.addAttribute("esEdicion", true);

            return "recetas/generar";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al obtener la receta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/recetas/historial";
        }
    }

    /**
     * Actualizar receta existente
     */
    @PostMapping("/actualizar")
    public String actualizarReceta(@Valid @ModelAttribute("receta") Receta receta,
                                   BindingResult result,
                                   @RequestParam(value = "medicamentoIds", required = false) List<Long> medicamentoIds,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {

        try {
            // Validar fechas
            if (receta.getFechaVencimiento().isBefore(receta.getFechaEmision())) {
                result.rejectValue("fechaVencimiento", "error.receta",
                        "La fecha de vencimiento debe ser posterior a la fecha de emisión");
            }

            if (result.hasErrors()) {
                model.addAttribute("pacientes", pacienteService.listarPacientes());
                model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
                model.addAttribute("esEdicion", true);
                return "recetas/generar";
            }

            // Asignar medicamentos si se proporcionaron
            if (medicamentoIds != null && !medicamentoIds.isEmpty()) {
                List<Medicamento> medicamentos = medicamentoService.obtenerMedicamentosPorIds(medicamentoIds);
                receta.setMedicamentos(medicamentos);
            }

            // Actualizar la receta
            Receta recetaActualizada = recetaService.actualizarReceta(receta);

            redirectAttributes.addFlashAttribute("mensaje", "Receta actualizada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");

            return "redirect:/recetas/detalle/" + recetaActualizada.getIdReceta();

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar la receta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/recetas/historial";
        }
    }

    /**
     * Eliminar receta
     */
    @PostMapping("/eliminar/{id}")
    public String eliminarReceta(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            recetaService.eliminarReceta(id);
            redirectAttributes.addFlashAttribute("mensaje", "Receta eliminada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar la receta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }
        return "redirect:/recetas/historial";
    }


    // ===== MÉTODOS DE UTILIDAD =====

    /**
     * Manejar errores generales del controlador
     */
    @ExceptionHandler(RuntimeException.class)
    public String manejarErrorGeneral(RuntimeException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensaje", "Error: " + e.getMessage());
        redirectAttributes.addFlashAttribute("tipo", "error");
        return "redirect:/recetas";
    }
}