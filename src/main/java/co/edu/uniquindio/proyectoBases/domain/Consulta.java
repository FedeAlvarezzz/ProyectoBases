package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "consulta")
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta", nullable = false, unique = true)
    private Long idConsulta;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    @NotNull(message = "El médico es obligatorio")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    @NotNull(message = "El paciente es obligatorio")
    private Paciente paciente;

    @Column(name = "fecha", nullable = false)
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    @NotNull(message = "La hora es obligatoria")
    private LocalTime hora;

    @Column(name = "valor", nullable = false)
    @DecimalMin(value = "0.0", message = "El valor no puede ser negativo")
    private double valor;

    @Column(name = "motivo", nullable = false, length = 500)
    @NotBlank(message = "El motivo es obligatorio")
    @Size(max = 500, message = "El motivo no puede exceder los 500 caracteres")
    private String motivo;

    @Column(name = "diagnostico", length = 1000)
    @Size(max = 1000, message = "El diagnóstico no puede exceder los 1000 caracteres")
    private String diagnostico;
}