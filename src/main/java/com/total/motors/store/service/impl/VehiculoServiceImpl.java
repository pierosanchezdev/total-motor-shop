package com.total.motors.store.service.impl;

import com.total.motors.store.dao.VehiculoDao;
import com.total.motors.store.entity.Vehiculo;
import com.total.motors.store.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoDao vehiculoDao;

    @Override
    public Page<Vehiculo> listarVehiculosPaginable(Pageable pageable) {
        return vehiculoDao.findAll(pageable);
    }

    @Override
    public List<Vehiculo> listarVehiculos() {
        return vehiculoDao.findAll();
    }

    @Override
    public List<Vehiculo> obtenerVehiculosPorId(List<Long> ids) {
        return vehiculoDao.findAllById(ids);
    }

    @Override
    public Optional<Vehiculo> obtenerVehiculoPorId(Long id) {
        return vehiculoDao.findById(id);
    }

    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return vehiculoDao.save(vehiculo);
    }

    @Override
    public void eliminarVehiculo(Long id) {
        vehiculoDao.deleteById(id);
    }
}
