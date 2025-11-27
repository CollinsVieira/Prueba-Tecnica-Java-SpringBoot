package com.pruebaTecnica.pruebaTecnica.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDto {
    private Long id;
    private String nombreProducto;
    private Integer cantidadProducto;
    private Double precio;
    private double subTotal;
}
