package com.pruebaTecnica.pruebaTecnica.repository;

import com.pruebaTecnica.pruebaTecnica.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta,Long> {
}
