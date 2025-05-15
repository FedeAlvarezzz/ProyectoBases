package co.edu.uniquindio.proyectoBases.services.interfaces;

import co.edu.uniquindio.proyectoBases.domain.HistoriaClinica;

import java.util.List;

public interface HistoriaClinicaService {

    HistoriaClinica crearHistoriaClinica(HistoriaClinica historiaClinica);
    HistoriaClinica obtenerHistoriaClinica(Long idHistoriaClinica);
    HistoriaClinica actualizarHistoriaClinica(HistoriaClinica historiaClinica);
    void eliminarHistoriaClinica(Long idHistoriaClinica);
    List<HistoriaClinica> listarHistoriasClinicas();
}
