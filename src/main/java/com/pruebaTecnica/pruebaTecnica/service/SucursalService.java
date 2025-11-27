package com.pruebaTecnica.pruebaTecnica.service;

import com.pruebaTecnica.pruebaTecnica.dto.SucursalDto;
import com.pruebaTecnica.pruebaTecnica.exception.NotFoundException;
import com.pruebaTecnica.pruebaTecnica.mapper.Mapper;
import com.pruebaTecnica.pruebaTecnica.model.Sucursal;
import com.pruebaTecnica.pruebaTecnica.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    SucursalRepository repository;

    @Override
    public List<SucursalDto> traerSucursales() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDto crearSucursal(SucursalDto sucursalDto) {
        Sucursal aux = Sucursal.builder()
                .id(sucursalDto.getId())
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();
        return Mapper.toDTO(repository.save(aux));
    }

    @Override
    public SucursalDto actualizarSucursal(Long id, SucursalDto sucursalDto) {
        Sucursal aux = repository.findById(id).orElseThrow(()-> new NotFoundException("No se encontro la sucursal con el id: " + id));

        aux.setNombre(sucursalDto.getNombre());
        aux.setDireccion(sucursalDto.getDireccion());
        return Mapper.toDTO(repository.save(aux));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if(!repository.existsById(id)){
            throw new NotFoundException("No se encontro la sucursal con el id: " + id);
        }
        repository.deleteById(id);

    }
}
