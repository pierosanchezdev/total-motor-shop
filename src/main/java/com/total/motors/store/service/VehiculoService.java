package com.total.motors.store.service;

import com.total.motors.store.entity.Vehiculo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehiculoService {
    Page<Vehiculo> listarVehiculosPaginable(int page);
    List<Vehiculo> listarVehiculos();
    void crearVehiculo(Vehiculo Vehiculo);
    Vehiculo obtenerVehiculoPorId(Long id);
    void actualizarVehiculo(Vehiculo Vehiculo);
    void eliminarVehiculo(Long id);
    List<Vehiculo> obtenerVehiculosPorId(List<Long> ids);
}
