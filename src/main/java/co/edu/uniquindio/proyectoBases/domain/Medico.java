package co.edu.uniquindio.proyectoBases.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "medico")
public class Medico {

    @Id
    @Column(name = "cedula", length = 20, nullable = false, unique = true)
    private String cedula;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "especialidad", length = 100, nullable = false)
    private String especialidad;

    @Column(name = "usuario", length = 50, nullable = false, unique = true)
    private String usuario;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;
}
