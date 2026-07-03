package com.example.aapos.repository;

import com.example.aapos.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByMedidorIdMedidor(Long idMedidor);
    List<Factura> findByPagada(Boolean pagada);
}
