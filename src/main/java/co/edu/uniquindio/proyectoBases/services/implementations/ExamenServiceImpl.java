package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.Examen;
import co.edu.uniquindio.proyectoBases.repositories.ExamenRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.ExamenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamenServiceImpl implements ExamenService {

    private final ExamenRepository examenRepo;

    @Override
    public Examen crearExamen(Examen examen) {
        if (examenRepo.existsByIdExamen(examen.getIdExamen())) {
            throw new RuntimeException("Ya existe un examen con ese ID");
        }
        return examenRepo.save(examen);
    }

    @Override
    public Optional<Examen> obtenerExamen(String idExamen) {
        if (!examenRepo.existsByIdExamen(idExamen)) {
            throw new RuntimeException("No existe un examen con ese ID");
        }
        return examenRepo.findByIdExamen(idExamen);
    }

    @Override
    public Examen actualizarExamen(Examen examen) {
        if (!examenRepo.existsByIdExamen(examen.getIdExamen())) {
            throw new RuntimeException("No existe un examen con ese ID");
        }
        return examenRepo.save(examen);
    }

    @Override
    public void eliminarExamen(String idExamen) {
        if (!examenRepo.existsByIdExamen(idExamen)) {
            throw new RuntimeException("No existe un examen con ese ID");
        }
        examenRepo.deleteById(idExamen);
    }

    @Override
    public List<Examen> listarExamenes() {
        return examenRepo.findAll();
    }
}