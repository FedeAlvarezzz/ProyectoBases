package co.edu.uniquindio.proyectoBases.controllers.api;

import co.edu.uniquindio.proyectoBases.domain.Receta;
import co.edu.uniquindio.proyectoBases.services.interfaces.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping
    public ResponseEntity<Receta> crearReceta(@RequestBody @Valid Receta receta) {
        Receta nuevaReceta = recetaService.crearReceta(receta);
        return ResponseEntity.ok(nuevaReceta);
    }

    // Otros métodos para obtener, actualizar y eliminar recetas pueden ser añadidos aquí
    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerReceta(@PathVariable Long id) {
         Receta receta = recetaService.obtenerReceta(id);
         return ResponseEntity.ok(receta);
    }

    // @PutMapping("/{id}")

}
