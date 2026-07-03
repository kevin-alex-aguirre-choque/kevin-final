package com.example.aapos.service;

import com.example.aapos.model.Reclamo;
import com.example.aapos.repository.ReclamoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamoService {

    private final ReclamoRepository repository;

    public ReclamoService(ReclamoRepository repository) {
        this.repository = repository;
    }

    public List<Reclamo> listarTodos() {
        return repository.findAll();
    }

    public List<Reclamo> listarPorCliente(Long idCliente) {
        return repository.findByClienteIdCliente(idCliente);
    }

    public List<Reclamo> listarPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    public Optional<Reclamo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Reclamo guardar(Reclamo reclamo) {
        reclamo.setIdReclamo(null);
        if (reclamo.getFechaReclamo() == null) {
            reclamo.setFechaReclamo(LocalDate.now());
        }
        if (reclamo.getEstado() == null) {
            reclamo.setEstado("PENDIENTE");
        }
        return repository.save(reclamo);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Reclamo> actualizarEstado(Long id, String nuevoEstado) {
        Optional<Reclamo> existente = repository.findById(id);
        if (existente.isPresent()) {
            Reclamo r = existente.get();
            r.setEstado(nuevoEstado);
            return Optional.of(repository.save(r));
        }
        return Optional.empty();
    }

    public Optional<Reclamo> actualizar(Long id, Reclamo reclamoActualizado) {
        Optional<Reclamo> existente = repository.findById(id);
        if (existente.isPresent()) {
            Reclamo r = existente.get();
            r.setTipo(reclamoActualizado.getTipo());
            r.setDescripcion(reclamoActualizado.getDescripcion());
            r.setEstado(reclamoActualizado.getEstado());
            return Optional.of(repository.save(r));
        }
        return Optional.empty();
    }
}
