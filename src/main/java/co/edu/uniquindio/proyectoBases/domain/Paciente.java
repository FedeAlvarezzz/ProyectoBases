package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "paciente")
public class Paciente {

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

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @DecimalMin(value = "0.5", message = "La altura mínima es 0.5 metros")
    @DecimalMax(value = "2.5", message = "La altura máxima es 2.5 metros")
    @Column(name = "altura", nullable = false)
    private double altura;

    @DecimalMin(value = "1.0", message = "El peso mínimo es 1 kg")
    @DecimalMax(value = "300.0", message = "El peso máximo es 300 kg")
    @Column(name = "peso", nullable = false)
    private double peso;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un email válido")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "El teléfono debe tener entre 10 y 15 dígitos")
    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
    @Column(name = "direccion", length = 200, nullable = false)
    private String direccion;

    @NotBlank(message = "El grupo sanguíneo es obligatorio")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "El grupo sanguíneo debe ser A+, A-, B+, B-, AB+, AB-, O+ o O-")
    @Column(name = "grupo_sanguineo", length = 10, nullable = false)
    private String grupoSanguineo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Column(name = "contrasena", length = 20, nullable = false)
    private String contrasena;

    //@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Consulta> consultas;
}
