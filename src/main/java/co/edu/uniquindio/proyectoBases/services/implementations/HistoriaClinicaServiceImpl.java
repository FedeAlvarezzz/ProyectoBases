package co.edu.uniquindio.proyectoBases.services.implementations;

import co.edu.uniquindio.proyectoBases.domain.HistoriaClinica;
import co.edu.uniquindio.proyectoBases.repositories.HistoriaClinicaRepository;
import co.edu.uniquindio.proyectoBases.services.interfaces.HistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public HistoriaClinica crearHistoriaClinica(HistoriaClinica historiaClinica) {

        if (historiaClinica.getIdHistoriaClinica() != null &&
                historiaClinicaRepository.existsById(historiaClinica.getIdHistoriaClinica())) {
            throw new RuntimeException("Ya existe una historia clínica con ese ID");
        }

        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public HistoriaClinica obtenerHistoriaClinica(Long idHistoriaClinica) {
        if (!historiaClinicaRepository.existsById(idHistoriaClinica)) {
            throw new RuntimeException("No existe una historia clínica con ese ID");
        }

        return historiaClinicaRepository.findById(idHistoriaClinica).orElse(null);
    }

    @Override
    public HistoriaClinica actualizarHistoriaClinica(HistoriaClinica historiaClinica) {
        if (!historiaClinicaRepository.existsById(historiaClinica.getIdHistoriaClinica())) {
            throw new RuntimeException("No existe una historia clínica con ese ID");
        }

        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public void eliminarHistoriaClinica(Long idHistoriaClinica) {
        if (!historiaClinicaRepository.existsById(idHistoriaClinica)) {
            throw new RuntimeException("No existe una historia clínica con ese ID");
        }

        historiaClinicaRepository.deleteById(idHistoriaClinica);

    }

    @Override
    public List<HistoriaClinica> listarHistoriasClinicas() {
        return historiaClinicaRepository.findAll();
    }


}
