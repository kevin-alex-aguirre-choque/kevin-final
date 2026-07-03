package com.example.aapos.controller;

import com.example.aapos.model.Medidor;
import com.example.aapos.service.MedidorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medidores")
@CrossOrigin("*")
public class MedidorController {

    private final MedidorService service;

    public MedidorController(MedidorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Medidor> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Medidor> listarPorCliente(@PathVariable Long idCliente) {
        return service.listarPorCliente(idCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medidor> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medidor> guardar(@Valid @RequestBody Medidor medidor) {
        return ResponseEntity.ok(service.guardar(medidor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medidor> actualizar(@PathVariable Long id, @Valid @RequestBody Medidor medidor) {
        return service.actualizar(id, medidor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
