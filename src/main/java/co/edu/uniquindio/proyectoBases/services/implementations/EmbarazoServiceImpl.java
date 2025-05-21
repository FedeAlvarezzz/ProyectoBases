package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.Embarazo;
import co.edu.uniquindio.proyectoBases.repositories.EmbarazoRepo;
import co.edu.uniquindio.proyectoBases.services.interfaces.EmbarazoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmbarazoServiceImpl implements EmbarazoService {

    private final EmbarazoRepo embarazoRepo;

    @Override
    public Embarazo crearEmbarazo(Embarazo embarazo) {
        if (embarazoRepo.existsById(embarazo.getIdEmbarazo())) {
            throw new RuntimeException("Ya existe un embarazo con ese ID");
        }
        return embarazoRepo.save(embarazo);
    }

    @Override
    public Optional<Embarazo> obtenerEmbarazo(String id) {
        if (!embarazoRepo.existsById(id)) {
            throw new RuntimeException("No existe un embarazo con ese ID");
        }
        return embarazoRepo.findById(id);
    }

    @Override
    public Embarazo actualizarEmbarazo(Embarazo embarazo) {
        if (!embarazoRepo.existsById(embarazo.getIdEmbarazo())) {
            throw new RuntimeException("No existe un embarazo con ese ID");
        }
        return embarazoRepo.save(embarazo);
    }

    @Override
    public void eliminarEmbarazo(String id) {
        if (!embarazoRepo.existsById(id)) {
            throw new RuntimeException("No existe un embarazo con ese ID");
        }
        embarazoRepo.deleteById(id);
    }

    @Override
    public List<Embarazo> listarEmbarazos() {
        return embarazoRepo.findAll();
    }
}