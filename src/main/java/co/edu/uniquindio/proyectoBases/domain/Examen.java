package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "examen")
public class Examen {

    @Id
    @NotBlank(message = "El ID del examen es obligatorio")
    @Size(max = 20, message = "El ID del examen no puede exceder los 20 caracteres")
    @Column(name = "id_examen", length = 20, nullable = false, unique = true)
    private String idExamen;

    @NotNull(message = "La fecha de resultado no puede ser nula")
    @PastOrPresent(message = "La fecha de resultado debe ser en el pasado o presente")
    @Column(name = "fecha_resultado", nullable = false)
    private LocalDate fechaResultado;

    @NotBlank(message = "El resultado es obligatorio")
    @Size(max = 500, message = "El resultado no puede exceder los 500 caracteres")
    @Column(name = "resultado", length = 500, nullable = false)
    private String resultado;

    @NotNull(message = "La fecha de realización no puede ser nula")
    @PastOrPresent(message = "La fecha de realización debe ser en el pasado o presente")
    @Column(name = "fecha_realizacion", nullable = false)
    private LocalDate fechaRealizacion;

    @NotBlank(message = "El tipo de examen es obligatorio")
    @Size(max = 100, message = "El tipo de examen no puede exceder los 100 caracteres")
    @Column(name = "tipo_examen", length = 100, nullable = false)
    private String tipoExamen;
}