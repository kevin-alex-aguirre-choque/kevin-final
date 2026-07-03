package com.example.aapos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Long idCliente;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El CI es obligatorio")
    @Column(name = "ci", nullable = false, unique = true, length = 20)
    private String ci;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    public Cliente() {}

    public Cliente(Long idCliente, String nombre, String ci, String direccion, String telefono, LocalDate fechaRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.ci = ci;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
