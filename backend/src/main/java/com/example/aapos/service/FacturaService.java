package com.example.aapos.service;

import com.example.aapos.model.Factura;
import com.example.aapos.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    // Tarifa referencial por m3 (ajustar segun tarifario real de AAPOS)
    private static final BigDecimal TARIFA_POR_M3 = new BigDecimal("3.50");

    private final FacturaRepository repository;

    public FacturaService(FacturaRepository repository) {
        this.repository = repository;
    }

    public List<Factura> listarTodas() {
        return repository.findAll();
    }

    public List<Factura> listarPorMedidor(Long idMedidor) {
        return repository.findByMedidorIdMedidor(idMedidor);
    }

    public List<Factura> listarPendientes() {
        return repository.findByPagada(false);
    }

    public Optional<Factura> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Factura guardar(Factura factura) {
        factura.setIdFactura(null);
        calcularConsumoYMonto(factura);
        if (factura.getFechaEmision() == null) {
            factura.setFechaEmision(LocalDate.now());
        }
        if (factura.getFechaVencimiento() == null) {
            factura.setFechaVencimiento(factura.getFechaEmision().plusDays(15));
        }
        if (factura.getPagada() == null) {
            factura.setPagada(false);
        }
        return repository.save(factura);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Factura> actualizar(Long id, Factura facturaActualizada) {
        Optional<Factura> existente = repository.findById(id);
        if (existente.isPresent()) {
            Factura f = existente.get();
            f.setLecturaAnterior(facturaActualizada.getLecturaAnterior());
            f.setLecturaActual(facturaActualizada.getLecturaActual());
            f.setPagada(facturaActualizada.getPagada());
            calcularConsumoYMonto(f);
            return Optional.of(repository.save(f));
        }
        return Optional.empty();
    }

    public Optional<Factura> marcarComoPagada(Long id) {
        Optional<Factura> existente = repository.findById(id);
        if (existente.isPresent()) {
            Factura f = existente.get();
            f.setPagada(true);
            return Optional.of(repository.save(f));
        }
        return Optional.empty();
    }

    private void calcularConsumoYMonto(Factura factura) {
        if (factura.getLecturaAnterior() != null && factura.getLecturaActual() != null) {
            int consumo = factura.getLecturaActual() - factura.getLecturaAnterior();
            factura.setConsumoM3(consumo);
            factura.setMontoTotal(TARIFA_POR_M3.multiply(BigDecimal.valueOf(consumo)));
        }
    }
}
