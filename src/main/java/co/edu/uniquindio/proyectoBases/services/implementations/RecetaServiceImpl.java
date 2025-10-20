package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.Receta;
import co.edu.uniquindio.proyectoBases.repositories.RecetaRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository recetaRepository;


    @Override
    public Receta crearReceta(Receta receta) {
        if (recetaRepository.existsById(receta.getIdReceta())) {
            throw new RuntimeException("Ya existe una receta con ese ID");
        }
        return recetaRepository.save(receta);
    }

    @Override
    public Receta obtenerReceta(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new RuntimeException("No existe una receta con ese ID");
        }
        return recetaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Receta> listarRecetas() {
        return recetaRepository.findAll();
    }

    @Override
    public void eliminarReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe una receta con ese ID"));

        recetaRepository.delete(receta);
    }

    @Override
    public Receta actualizarReceta(Receta receta) {
        return null;
    }
}
