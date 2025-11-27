package com.pruebaTecnica.pruebaTecnica.service;

import com.pruebaTecnica.pruebaTecnica.dto.SucursalDto;
import com.pruebaTecnica.pruebaTecnica.model.Sucursal;

import java.util.List;

public interface ISucursalService {
    List<SucursalDto> traerSucursales();
    SucursalDto crearSucursal(SucursalDto sucursalDto);
    SucursalDto actualizarSucursal(Long id, SucursalDto sucursalDto);
    void eliminarSucursal(Long id);
}
