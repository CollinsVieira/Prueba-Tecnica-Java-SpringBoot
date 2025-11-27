package com.pruebaTecnica.pruebaTecnica.controller;

import com.pruebaTecnica.pruebaTecnica.dto.VentaDto;
import com.pruebaTecnica.pruebaTecnica.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDto>> traerVentas() {
        return ResponseEntity.ok(ventaService.traerVentas());
    }

    /**
     * Crea una venta usando directamente VentaDTO en la request (opción simple, sin request separado).
     * Se espera que el DTO traiga la información
     *
     */
    @PostMapping
    public ResponseEntity<VentaDto> create(@RequestBody VentaDto dto) {
        VentaDto created = ventaService.crearVenta(dto);
        return ResponseEntity.created(URI.create("/api/ventas/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public VentaDto actualizar(@PathVariable Long id, @RequestBody VentaDto dto) {
        // Actualiza fecha, estado, idSucursal, total y reemplaza el detalle

        return ventaService.actualizarVenta(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
