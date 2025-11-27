package com.pruebaTecnica.pruebaTecnica.mapper;

import com.pruebaTecnica.pruebaTecnica.dto.DetalleVentaDto;
import com.pruebaTecnica.pruebaTecnica.dto.ProductoDto;
import com.pruebaTecnica.pruebaTecnica.dto.SucursalDto;
import com.pruebaTecnica.pruebaTecnica.dto.VentaDto;
import com.pruebaTecnica.pruebaTecnica.model.DetalleVenta;
import com.pruebaTecnica.pruebaTecnica.model.Producto;
import com.pruebaTecnica.pruebaTecnica.model.Sucursal;
import com.pruebaTecnica.pruebaTecnica.model.Venta;

import java.util.stream.Collectors;

public class Mapper {
    //Mapeo de Producto a ProductoDTO

    public static ProductoDto toDTO(Producto p) {
        if(p == null) return null;

        return ProductoDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }
    //Mapeo de Venta a VentaDTO
    public static VentaDto toDTO(Venta e) {
        if(e == null) return null;
        var detalle = e.getDetalleVenta().stream().map(det -> DetalleVentaDto.builder()
                .id(det.getId())
                .nombreProducto(det.getProducto().getNombre())
                .cantidadProducto(det.getCantidadProducto())
                .precio(det.getPrecio())
                .subTotal(det.getPrecio() * det.getCantidadProducto())
                .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetalleVentaDto::getSubTotal)
                .reduce(0.0, Double::sum);
        return VentaDto.builder()
                .id(e.getId())
                .fecha(e.getFecha())
                .estado(e.getEstado())
                .total(total)
                .detalle(detalle)
                .idSucursal(e.getSucursal().getId())
                .build();
    }
    //Mapeo de Sucursal a SucursalDTO
    public static SucursalDto toDTO(Sucursal e) {
        if(e == null) return null;
        return SucursalDto.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .direccion(e.getDireccion())
                .build();
    }
}
