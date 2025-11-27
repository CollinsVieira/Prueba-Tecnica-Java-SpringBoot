package com.pruebaTecnica.pruebaTecnica.service;

import com.pruebaTecnica.pruebaTecnica.dto.ProductoDto;
import com.pruebaTecnica.pruebaTecnica.exception.NotFoundException;
import com.pruebaTecnica.pruebaTecnica.mapper.Mapper;
import com.pruebaTecnica.pruebaTecnica.model.Producto;
import com.pruebaTecnica.pruebaTecnica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<ProductoDto> traerProductos() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDto crearProducto(ProductoDto productoDto) {
        Producto aux = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();
        return Mapper.toDTO(repository.save(aux));
    }

    @Override
    public ProductoDto actualizarProducto(Long id, ProductoDto productoDto) {
        Producto aux = repository.findById(id).orElseThrow(()-> new NotFoundException("No se encontro el producto con el id: " + id));

        aux.setNombre(productoDto.getNombre());
        aux.setCategoria(productoDto.getCategoria());
        aux.setCantidad(productoDto.getCantidad());
        aux.setPrecio(productoDto.getPrecio());
        return Mapper.toDTO(repository.save(aux));
    }


    @Override
    public void eliminarProducto(Long id) {
        if(!repository.existsById(id)){
            throw new NotFoundException("No se encontro el producto con el id: " + id);
        }
        repository.deleteById(id);

    }
}
