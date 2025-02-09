package com.total.motors.store.service;

import com.total.motors.store.entity.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    List<Vehiculo> listarVehiculos();
    List<Vehiculo> obtenerVehiculosPorId(List<Long> ids);
    Page<Vehiculo> listarVehiculosPaginable(Pageable pageable);
    Optional<Vehiculo> obtenerVehiculoPorId(Long id);
    Vehiculo guardarVehiculo(Vehiculo vehiculo);
    void eliminarVehiculo(Long id);
}
