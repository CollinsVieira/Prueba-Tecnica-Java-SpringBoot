package com.pruebaTecnica.pruebaTecnica.service;

import com.pruebaTecnica.pruebaTecnica.dto.ProductoDto;

import java.util.List;

public interface IProductoService {
    List<ProductoDto> traerProductos();
    ProductoDto crearProducto(ProductoDto productoDto);
    ProductoDto actualizarProducto(Long id, ProductoDto productoDto);
    void eliminarProducto(Long id);
}
