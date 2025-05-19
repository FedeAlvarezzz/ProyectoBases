package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "historia_clinica")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historia_clinica")
    private Long idHistoriaClinica;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
    private List<Alergia> alergias;

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
    private List<Enfermedad> enfermedades;

    @OneToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

}
