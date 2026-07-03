package com.example.aapos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "medidor")
public class Medidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmedidor")
    private Long idMedidor;

    @NotBlank(message = "El numero de medidor es obligatorio")
    @Column(name = "numero_medidor", nullable = false, unique = true, length = 30)
    private String numeroMedidor;

    @Column(name = "direccion_instalacion", length = 150)
    private String direccionInstalacion;

    @Column(name = "fecha_instalacion")
    private LocalDate fechaInstalacion;

    @Column(name = "estado", length = 20)
    private String estado; // ACTIVO, INACTIVO, EN_MANTENIMIENTO

    @ManyToOne
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;

    public Medidor() {}

    public Medidor(Long idMedidor, String numeroMedidor, String direccionInstalacion,
                    LocalDate fechaInstalacion, String estado, Cliente cliente) {
        this.idMedidor = idMedidor;
        this.numeroMedidor = numeroMedidor;
        this.direccionInstalacion = direccionInstalacion;
        this.fechaInstalacion = fechaInstalacion;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Long getIdMedidor() { return idMedidor; }
    public void setIdMedidor(Long idMedidor) { this.idMedidor = idMedidor; }

    public String getNumeroMedidor() { return numeroMedidor; }
    public void setNumeroMedidor(String numeroMedidor) { this.numeroMedidor = numeroMedidor; }

    public String getDireccionInstalacion() { return direccionInstalacion; }
    public void setDireccionInstalacion(String direccionInstalacion) { this.direccionInstalacion = direccionInstalacion; }

    public LocalDate getFechaInstalacion() { return fechaInstalacion; }
    public void setFechaInstalacion(LocalDate fechaInstalacion) { this.fechaInstalacion = fechaInstalacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
