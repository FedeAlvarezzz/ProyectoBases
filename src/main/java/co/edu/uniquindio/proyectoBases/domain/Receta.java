package co.edu.uniquindio.proyectoBases.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "receta")
@Getter
@Setter
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    @ManyToOne
    private Paciente paciente;

    @Column(name = "fechaEmision", nullable = false)
    @PastOrPresent
    private LocalDate fechaEmision;

    @Column(name = "fechaVencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "observacion", nullable = false)
    private String observacion;

    @OneToMany(mappedBy = "receta")
    private List<Medicamento> medicamentos;

}
