package co.edu.uniquindio.proyectoBases.domain;

import co.edu.uniquindio.proyectoBases.domain.enums.EstadoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @NotNull(message = "La cédula es obligatoria")
    @Column(name = "cedula", length = 20, nullable = false, unique = true)
    private Integer cedula;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede exceder los 100 caracteres")
    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un email válido")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "El teléfono debe tener entre 10 y 15 dígitos")
    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 100, message = "La especialidad no puede exceder los 100 caracteres")
    @Column(name = "especialidad", length = 100, nullable = false)
    private String especialidad;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
}