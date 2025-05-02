package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta", nullable = false, unique = true)
    private Long idConsulta;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "motivo", nullable = false, length = 500)
    private String motivo;

    @Column(name = "diagnostico", length = 1000)
    private String diagnostico;
}
