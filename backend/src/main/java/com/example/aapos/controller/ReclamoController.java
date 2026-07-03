package com.example.aapos.controller;

import com.example.aapos.model.Reclamo;
import com.example.aapos.service.ReclamoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reclamos")
@CrossOrigin("*")
public class ReclamoController {

    private final ReclamoService service;

    public ReclamoController(ReclamoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reclamo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Reclamo> listarPorCliente(@PathVariable Long idCliente) {
        return service.listarPorCliente(idCliente);
    }

    @GetMapping("/estado/{estado}")
    public List<Reclamo> listarPorEstado(@PathVariable String estado) {
        return service.listarPorEstado(estado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reclamo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reclamo> guardar(@Valid @RequestBody Reclamo reclamo) {
        return ResponseEntity.ok(service.guardar(reclamo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reclamo> actualizar(@PathVariable Long id, @Valid @RequestBody Reclamo reclamo) {
        return service.actualizar(id, reclamo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Reclamo> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.actualizarEstado(id, body.get("estado"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
