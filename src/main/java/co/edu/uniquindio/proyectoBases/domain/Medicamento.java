package co.edu.uniquindio.proyectoBases.domain;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Integer idMedicamento;

    @NotBlank(message = "El nombre del medicamento es obligatorio")
    @Size(min = 5, max = 100, message = "El nombre del medicamento debe tener entre 5 y 100 caracteres")
    @Column(name = "nombre_medicamento", length = 100, nullable = false)
    private String nombreMedicamento;

    @NotBlank(message = "La dosis es obligatoria")
    @Size(min = 5, max = 50, message = "La dosis debe tener entre 5 y 50 caracteres")
    @Column(name = "dosis", length = 50, nullable = false)
    private String dosis;

    @NotBlank(message = "La frecuencia es obligatoria")
    @Size(min = 5, max = 50, message = "La frecuencia debe tener entre 5 y 50 caracteres")
    @Column(name = "frecuencia", length = 50, nullable = false)
    private String frecuencia;

    @NotBlank(message = "La duración es obligatoria")
    @Size(min = 5, max = 50, message = "La duración debe tener entre 5 y 50 caracteres")
    @Column(name = "duracion", length = 50, nullable = false)
    private String duracion;

    @NotBlank(message = "Las recomendaciones son obligatorias")
    @Size(min = 5, max = 500, message = "Las recomendaciones deben tener entre 5 y 500 caracteres")
    @Column(name = "recomendaciones", length = 500, nullable = false)
    private String recomendaciones;
}