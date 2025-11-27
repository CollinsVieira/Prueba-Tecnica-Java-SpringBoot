package com.pruebaTecnica.pruebaTecnica.service;

import com.pruebaTecnica.pruebaTecnica.dto.DetalleVentaDto;
import com.pruebaTecnica.pruebaTecnica.dto.VentaDto;
import com.pruebaTecnica.pruebaTecnica.exception.NotFoundException;
import com.pruebaTecnica.pruebaTecnica.mapper.Mapper;
import com.pruebaTecnica.pruebaTecnica.model.DetalleVenta;
import com.pruebaTecnica.pruebaTecnica.model.Producto;
import com.pruebaTecnica.pruebaTecnica.model.Sucursal;
import com.pruebaTecnica.pruebaTecnica.model.Venta;
import com.pruebaTecnica.pruebaTecnica.repository.ProductoRepository;
import com.pruebaTecnica.pruebaTecnica.repository.SucursalRepository;
import com.pruebaTecnica.pruebaTecnica.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    SucursalRepository sucursalRepository;
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<VentaDto> traerVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDto> ventasDto = new ArrayList<>();
        VentaDto ventaDto;
        for( Venta venta : ventas) {
            ventaDto = Mapper.toDTO(venta);
            ventasDto.add(ventaDto);
        }
        return ventasDto;
    }

    @Override
    public VentaDto crearVenta(VentaDto ventaDto) {
        //Validaciones
        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar la sucursal
        Sucursal suc = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }

        //Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDto.getTotal());

        // La lista de detalles
        // --> Acá están los productos
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDto detDTO : ventaDto.getDetalle()) {
            // Buscar producto por id (tu detDTO usa id como id de producto)
            Producto p = productoRepository.findByNombre(detDTO.getNombreProducto()).orElse(null);
            if (p == null)
            {throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProducto());}

            //Crear detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProducto(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantidadProducto(detDTO.getCantidadProducto());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
            totalCalculado = totalCalculado+(detDTO.getPrecio()*detDTO.getCantidadProducto());

        }
        //Seteamos la lista de detalle Venta
        vent.setDetalleVenta(detalles);

        //guardamos en la BD
        vent = ventaRepository.save(vent);

        //Mapeo de salida
        VentaDto ventaSalida = Mapper.toDTO(vent);

        return ventaSalida;
    }

    @Override
    public VentaDto actualizarVenta(Long id, VentaDto ventaDto) {
        //buscar si la venta existe para actualizarla
        Venta v = ventaRepository.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if (ventaDto.getFecha()!=null) {
            v.setFecha(ventaDto.getFecha());
        }
        if(ventaDto.getEstado()!=null) {
            v.setEstado(ventaDto.getEstado());
        }

        if (ventaDto.getTotal()!=null) {
            v.setTotal(ventaDto.getTotal());
        }

        if (ventaDto.getIdSucursal()!=null) {
            Sucursal suc = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepository.save(v);

        VentaDto ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {

    }
}
