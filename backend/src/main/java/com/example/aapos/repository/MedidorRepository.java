package com.example.aapos.repository;

import com.example.aapos.model.Medidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidorRepository extends JpaRepository<Medidor, Long> {
    List<Medidor> findByClienteIdCliente(Long idCliente);
}
