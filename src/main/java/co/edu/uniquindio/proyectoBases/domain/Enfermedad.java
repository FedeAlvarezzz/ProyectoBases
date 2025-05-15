package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enfermedad")
public class Enfermedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnfermedad;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_historia", referencedColumnName = "id_historia_clinica")
    private HistoriaClinica historiaClinica;


}

