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
@Table(name = "metodo_anticonceptivo")
public class MetodoAnticonceptivo {

    @Id
    @NotBlank(message = "El ID del método anticonceptivo es obligatorio")
    @Size(max = 20, message = "El ID no puede exceder los 20 caracteres")
    @Column(name = "id_metodo", length = 20, nullable = false, unique = true)
    private String idMetodoAnticonceptivo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @NotBlank(message = "La efectividad es obligatoria")
    @Size(max = 50, message = "La efectividad no puede exceder los 50 caracteres")
    @Column(name = "efectividad", length = 50, nullable = false)
    private String efectividad;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @PastOrPresent(message = "La fecha de inicio debe ser en el pasado o presente")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @FutureOrPresent(message = "La fecha de fin debe ser en el futuro o presente")
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Size(max = 500, message = "Las restricciones no pueden exceder los 500 caracteres")
    @Column(name = "restricciones", length = 500)
    private String restricciones;
}