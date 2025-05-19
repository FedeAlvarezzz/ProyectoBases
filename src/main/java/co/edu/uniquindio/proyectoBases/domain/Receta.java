package co.edu.uniquindio.proyectoBases.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table( name = "receta" )
@Getter
@Setter
public class Receta {

    @Id
    private Long idReceta;

    @Column(name = "fechaEmision", nullable = false)
    @Size(min = 10, max = 20)
    private LocalDate fechaEmision;

    @Column(name = "fechaVencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "observacion", nullable = false)
    private String observacion;

}
