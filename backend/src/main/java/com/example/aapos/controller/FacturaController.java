package com.example.aapos.controller;

import com.example.aapos.model.Factura;
import com.example.aapos.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin("*")
public class FacturaController {

    private final FacturaService service;

    public FacturaController(FacturaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Factura> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/medidor/{idMedidor}")
    public List<Factura> listarPorMedidor(@PathVariable Long idMedidor) {
        return service.listarPorMedidor(idMedidor);
    }

    @GetMapping("/pendientes")
    public List<Factura> listarPendientes() {
        return service.listarPendientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Factura> guardar(@Valid @RequestBody Factura factura) {
        return ResponseEntity.ok(service.guardar(factura));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizar(@PathVariable Long id, @Valid @RequestBody Factura factura) {
        return service.actualizar(id, factura)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<Factura> marcarComoPagada(@PathVariable Long id) {
        return service.marcarComoPagada(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
