package com.example.aapos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "reclamo")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreclamo")
    private Long idReclamo;

    @ManyToOne
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;

    @NotBlank(message = "El tipo de reclamo es obligatorio")
    @Column(name = "tipo", length = 50)
    private String tipo; // FUGA, MEDIDOR_DAÑADO, FACTURACION_INCORRECTA, CORTE_SERVICIO, OTRO

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "fecha_reclamo")
    private LocalDate fechaReclamo;

    @Column(name = "estado", length = 20)
    private String estado; // PENDIENTE, EN_PROCESO, RESUELTO, RECHAZADO

    public Reclamo() {}

    public Long getIdReclamo() { return idReclamo; }
    public void setIdReclamo(Long idReclamo) { this.idReclamo = idReclamo; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaReclamo() { return fechaReclamo; }
    public void setFechaReclamo(LocalDate fechaReclamo) { this.fechaReclamo = fechaReclamo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
