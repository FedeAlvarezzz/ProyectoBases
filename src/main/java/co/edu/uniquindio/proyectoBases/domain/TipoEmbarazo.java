package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipo_embarazo")
public class TipoEmbarazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_embarazo", nullable = false, unique = true)
    private Integer idTipoEmbarazo;

    @NotBlank(message = "La descripción del tipo de embarazo es obligatoria")
    @Size(min = 5, max = 100, message = "La descripción debe tener entre 5 y 100 caracteres")
    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    @NotBlank(message = "El riesgo asociado es obligatorio")
    @Size(min = 5, max = 50, message = "El riesgo asociado debe tener entre 5 y 50 caracteres")
    @Column(name = "riesgo_asociado", length = 50, nullable = false)
    private String riesgoAsociado;
}