package com.example.aapos.service;

import com.example.aapos.model.Medidor;
import com.example.aapos.repository.MedidorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedidorService {

    private final MedidorRepository repository;

    public MedidorService(MedidorRepository repository) {
        this.repository = repository;
    }

    public List<Medidor> listarTodos() {
        return repository.findAll();
    }

    public List<Medidor> listarPorCliente(Long idCliente) {
        return repository.findByClienteIdCliente(idCliente);
    }

    public Optional<Medidor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Medidor guardar(Medidor medidor) {
        medidor.setIdMedidor(null);
        if (medidor.getEstado() == null) {
            medidor.setEstado("ACTIVO");
        }
        return repository.save(medidor);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Medidor> actualizar(Long id, Medidor medidorActualizado) {
        Optional<Medidor> existente = repository.findById(id);
        if (existente.isPresent()) {
            Medidor m = existente.get();
            m.setNumeroMedidor(medidorActualizado.getNumeroMedidor());
            m.setDireccionInstalacion(medidorActualizado.getDireccionInstalacion());
            m.setEstado(medidorActualizado.getEstado());
            m.setCliente(medidorActualizado.getCliente());
            return Optional.of(repository.save(m));
        }
        return Optional.empty();
    }
}
