package com.example.aapos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactura")
    private Long idFactura;

    @ManyToOne
    @JoinColumn(name = "idmedidor", nullable = false)
    private Medidor medidor;

    @Column(name = "periodo", nullable = false, length = 7) // formato "2026-07"
    private String periodo;

    @Column(name = "lectura_anterior")
    private Integer lecturaAnterior;

    @Column(name = "lectura_actual")
    private Integer lecturaActual;

    @Column(name = "consumo_m3")
    private Integer consumoM3;

    @Column(name = "monto_total", precision = 10, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "pagada")
    private Boolean pagada;

    public Factura() {}

    public Long getIdFactura() { return idFactura; }
    public void setIdFactura(Long idFactura) { this.idFactura = idFactura; }

    public Medidor getMedidor() { return medidor; }
    public void setMedidor(Medidor medidor) { this.medidor = medidor; }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    public Integer getLecturaAnterior() { return lecturaAnterior; }
    public void setLecturaAnterior(Integer lecturaAnterior) { this.lecturaAnterior = lecturaAnterior; }

    public Integer getLecturaActual() { return lecturaActual; }
    public void setLecturaActual(Integer lecturaActual) { this.lecturaActual = lecturaActual; }

    public Integer getConsumoM3() { return consumoM3; }
    public void setConsumoM3(Integer consumoM3) { this.consumoM3 = consumoM3; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public Boolean getPagada() { return pagada; }
    public void setPagada(Boolean pagada) { this.pagada = pagada; }
}
