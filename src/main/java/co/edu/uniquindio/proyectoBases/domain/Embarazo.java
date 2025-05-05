package co.edu.uniquindio.proyectoBases.domain;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

public class Embarazo {

    @Id
    @NotBlank(message = "El ID del embarazo es obligatorio")
    @Size(min = 5, max = 20, message = "El ID del embarazo debe tener entre 5 y 20 caracteres")
    private String idEmbarazo;

    @NotBlank(message = "El ID del paciente es obligatorio")
    @Size(min = 5, max = 20, message = "El ID del paciente debe tener entre 5 y 20 caracteres")
    private String idPaciente;

    @NotBlank(message = "La fecha del último control es obligatoria")
    @Past(message = "La fecha del último control debe ser en el pasado")
    private String fechaUltimoControl;

    @NotBlank(message = "La fecha de parto esperado es obligatoria")
    @Future(message = "La fecha de parto esperado debe ser en el futuro")
    private String fechaPartoEsperado;
    
    @NotBlank(message = "Las semanas de gestación son obligatorias")
    @Size(min = 1, max = 2, message = "Las semanas de gestación deben tener entre 1 y 2 caracteres")
    private String semanasGestacion;

    @NotBlank(message = "El tipo de embarazo es obligatorio")
    private String tipoEmbarazo; // Normal o de alto riesgo

    private String observaciones;

    public Embarazo() {
    }

    public Embarazo(String idEmbarazo, String idPaciente, String fechaUltimoControl,
                    String fechaPartoEsperado, String semanasGestacion, String tipoEmbarazo, String observaciones) {
        this.idEmbarazo = idEmbarazo;
        this.idPaciente = idPaciente;
        this.fechaUltimoControl = fechaUltimoControl;
        this.fechaPartoEsperado = fechaPartoEsperado;
        this.semanasGestacion = semanasGestacion;
        this.tipoEmbarazo = tipoEmbarazo;
        this.observaciones = observaciones;
    }

    public String getIdEmbarazo() {
        return idEmbarazo;
    }

    public void setIdEmbarazo(String idEmbarazo) {
        this.idEmbarazo = idEmbarazo;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getFechaUltimoControl() {
        return fechaUltimoControl;
    }

    public void setFechaUltimoControl(String fechaUltimoControl) {
        this.fechaUltimoControl = fechaUltimoControl;
    }

    public String getFechaPartoEsperado() {
        return fechaPartoEsperado;
    }

    public void setFechaPartoEsperado(String fechaPartoEsperado) {
        this.fechaPartoEsperado = fechaPartoEsperado;
    }

    public String getSemanasGestacion() {
        return semanasGestacion;
    }

    public void setSemanasGestacion(String semanasGestacion) {
        this.semanasGestacion = semanasGestacion;
    }

    public String getTipoEmbarazo() {
        return tipoEmbarazo;
    }

    public void setTipoEmbarazo(String tipoEmbarazo) {
        this.tipoEmbarazo = tipoEmbarazo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
}
