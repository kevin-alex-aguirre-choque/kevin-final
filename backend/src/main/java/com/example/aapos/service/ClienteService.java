package com.example.aapos.service;

import com.example.aapos.model.Cliente;
import com.example.aapos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Cliente guardar(Cliente cliente) {
        cliente.setIdCliente(null); // aseguramos insert, no update accidental
        if (cliente.getFechaRegistro() == null) {
            cliente.setFechaRegistro(LocalDate.now());
        }
        return repository.save(cliente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Cliente> actualizar(Long id, Cliente clienteActualizado) {
        Optional<Cliente> existente = repository.findById(id);
        if (existente.isPresent()) {
            Cliente c = existente.get();
            c.setNombre(clienteActualizado.getNombre());
            c.setCi(clienteActualizado.getCi());
            c.setDireccion(clienteActualizado.getDireccion());
            c.setTelefono(clienteActualizado.getTelefono());
            return Optional.of(repository.save(c));
        }
        return Optional.empty();
    }
}
