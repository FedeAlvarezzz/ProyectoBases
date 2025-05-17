package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.Ciclo;
import co.edu.uniquindio.proyectoBases.repositories.CicloRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.CicloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CicloServiceImpl implements CicloService {

    private final CicloRepository cicloRepo;

    @Override
    public Ciclo crearCiclo(Ciclo ciclo) {
        if (cicloRepo.existsByIdCiclo(ciclo.getIdCiclo())) {
            throw new RuntimeException("Ya existe un ciclo con ese ID");
        }
        return cicloRepo.save(ciclo);
    }

    @Override
    public Optional<Ciclo> obtenerCiclo(int idCiclo) {
        if (!cicloRepo.existsByIdCiclo(idCiclo)) {
            throw new RuntimeException("No existe un ciclo con ese ID");
        }
        return cicloRepo.findByIdCiclo(idCiclo);
    }

    @Override
    public Ciclo actualizarCiclo(Ciclo ciclo) {
        if (!cicloRepo.existsByIdCiclo(ciclo.getIdCiclo())) {
            throw new RuntimeException("No existe un ciclo con ese ID");
        }
        return cicloRepo.save(ciclo);
    }

    @Override
    public void eliminarCiclo(int idCiclo) {
        if (!cicloRepo.existsByIdCiclo(idCiclo)) {
            throw new RuntimeException("No existe un ciclo con ese ID");
        }
        cicloRepo.deleteById(idCiclo);
    }

    @Override
    public List<Ciclo> listarCiclos() {
        return cicloRepo.findAll();
    }
}