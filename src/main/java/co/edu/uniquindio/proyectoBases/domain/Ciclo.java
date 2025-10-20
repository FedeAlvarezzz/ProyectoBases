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
@Table(name = "ciclo")
public class Ciclo {

    @Id
    @Positive(message = "El ID del ciclo debe ser un número positivo")
    @Column(name = "id_ciclo", nullable = false, unique = true)
    private int idCiclo;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de inicio debe ser en el pasado o presente")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede ser nula")
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Positive(message = "La duración debe ser un número positivo")
    @Max(value = 12, message = "La duración máxima es de 12 meses")
    @Column(name = "duracion", nullable = false)
    private int duracion;

    @Positive(message = "La intensidad debe ser un número positivo")
    @Min(value = 1, message = "La intensidad mínima es 1")
    @Max(value = 5, message = "La intensidad máxima es 5")
    @Column(name = "intensidad", nullable = false)
    private int intensidad;
}