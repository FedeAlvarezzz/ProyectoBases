package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.MetodoAnticonceptivo;
import co.edu.uniquindio.proyectoBases.repositories.MetodoAnticonceptivoRepository;
import co.edu.uniquindio.proyectoBases.repositories.MetodoAnticonceptivoRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.MetodoAnticonceptivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetodoAnticonceptivoServiceImpl implements MetodoAnticonceptivoService {

    private final MetodoAnticonceptivoRepository metodoRepo;

    @Override
    public MetodoAnticonceptivo crearMetodo(MetodoAnticonceptivo metodo) {
        if (metodoRepo.existsByIdMetodoAnticonceptivo(metodo.getIdMetodoAnticonceptivo())) {
            throw new RuntimeException("Ya existe un método anticonceptivo con ese ID");
        }
        return metodoRepo.save(metodo);
    }

    @Override
    public Optional<MetodoAnticonceptivo> obtenerMetodo(String idMetodo) {
        if (!metodoRepo.existsByIdMetodoAnticonceptivo(idMetodo)) {
            throw new RuntimeException("No existe un método anticonceptivo con ese ID");
        }
        return metodoRepo.findByIdMetodoAnticonceptivo(idMetodo);
    }

    @Override
    public MetodoAnticonceptivo actualizarMetodo(MetodoAnticonceptivo metodo) {
        if (!metodoRepo.existsByIdMetodoAnticonceptivo(metodo.getIdMetodoAnticonceptivo())) {
            throw new RuntimeException("No existe un método anticonceptivo con ese ID");
        }
        return metodoRepo.save(metodo);
    }

    @Override
    public void eliminarMetodo(String idMetodo) {
        if (!metodoRepo.existsByIdMetodoAnticonceptivo(idMetodo)) {
            throw new RuntimeException("No existe un método anticonceptivo con ese ID");
        }
        metodoRepo.deleteById(idMetodo);
    }

    @Override
    public List<MetodoAnticonceptivo> listarMetodos() {
        return metodoRepo.findAll();
    }
}