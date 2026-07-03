package com.example.aapos.repository;

import com.example.aapos.model.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Long> {
    List<Reclamo> findByClienteIdCliente(Long idCliente);
    List<Reclamo> findByEstado(String estado);
}
