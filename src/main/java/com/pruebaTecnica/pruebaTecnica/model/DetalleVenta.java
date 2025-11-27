package com.pruebaTecnica.pruebaTecnica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidadProducto;
    private double precio;
    //Venta
    @ManyToOne
    private Venta venta;

    //Producto
    @ManyToOne
    private Producto producto;
}
