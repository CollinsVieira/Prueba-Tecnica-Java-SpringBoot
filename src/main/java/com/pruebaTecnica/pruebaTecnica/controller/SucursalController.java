package com.pruebaTecnica.pruebaTecnica.controller;

import com.pruebaTecnica.pruebaTecnica.dto.SucursalDto;
import com.pruebaTecnica.pruebaTecnica.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDto>> traerSucursales() {
        return ResponseEntity.ok(sucursalService.traerSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDto> create(@RequestBody SucursalDto dto) {
        SucursalDto created = sucursalService.crearSucursal(dto);
        return ResponseEntity.created(URI.create("/api/sucursales/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> update(@PathVariable Long id, @RequestBody SucursalDto dto) {
        return ResponseEntity.ok(sucursalService.actualizarSucursal(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
