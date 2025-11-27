package com.pruebaTecnica.pruebaTecnica.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDto {
    //Datos propios de la venta
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;
    //Datos de la sucursal
    private Long idSucursal;

    //Lista de detalles
    private List<DetalleVentaDto> detalle;

    //Total de la venta

}
