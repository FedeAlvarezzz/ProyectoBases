package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alergia")
public class Alergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlergia;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_historia", referencedColumnName = "id_historia_clinica")
    private HistoriaClinica historiaClinica;


}
