package co.edu.uniquindio.proyectoBases.domain;

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
    @NotBlank(message = "La cédula es obligatoria")
    @Size(min = 5, max = 20, message = "La cédula debe tener entre 5 y 20 caracteres")
    @Column(name = "cedula", length = 20, nullable = false, unique = true)
    private String cedula;

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

    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 5, max = 50, message = "El usuario debe tener entre 5 y 50 caracteres")
    @Column(name = "usuario", length = 50, nullable = false, unique = true)
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;
}