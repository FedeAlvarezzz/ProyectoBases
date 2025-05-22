package co.edu.uniquindio.proyectoBases.domain;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "embarazo")
public class Embarazo {

    @Id
    @NotBlank(message = "El ID del embarazo es obligatorio")
    @Size(min = 5, max = 20, message = "El ID del embarazo debe tener entre 5 y 20 caracteres")
    @Column(name = "id_embarazo", length = 20, nullable = false, unique = true)
    private String idEmbarazo;

    @NotNull(message = "La fecha de concepción es obligatoria")
    @Past(message = "La fecha de concepción debe ser en el pasado")
    @Column(name = "fecha_concepcion", nullable = false)
    private LocalDate fechaConcepcion;

    @NotNull(message = "La fecha de parto esperado es obligatoria")
    @Future(message = "La fecha de parto esperado debe ser en el futuro")
    @Column(name = "fecha_parto_esperado", nullable = false)
    private LocalDate fechaPartoEsperado;

    @NotNull(message = "Las semanas de gestación son obligatorias")
    @Min(value = 1, message = "Las semanas de gestación deben ser al menos 1")
    @Max(value = 42, message = "Las semanas de gestación no pueden exceder 42")
    @Column(name = "semanas_gestacion", nullable = false)
    private Integer semanasGestacion;

    @ManyToOne
    @JoinColumn(name = "tipo_embarazo_id", nullable = false)
    @NotNull(message = "El tipo de embarazo es obligatorio")
    private TipoEmbarazo tipoEmbarazo;
}