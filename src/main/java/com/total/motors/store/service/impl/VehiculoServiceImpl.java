package com.total.motors.store.service.impl;

import com.total.motors.store.dao.VehiculoDao;
import com.total.motors.store.entity.Vehiculo;
import com.total.motors.store.entity.Vehiculo;
import com.total.motors.store.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoDao vehiculoDao;

    @Override
    public Page<Vehiculo> listarVehiculosPaginable(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return vehiculoDao.findAll(pageable);
    }

    @Override
    public List<Vehiculo> listarVehiculos() {
        return vehiculoDao.findAll();
    }

    @Override
    public void crearVehiculo(Vehiculo vehiculo) {
        vehiculoDao.save(vehiculo);
    }

    @Override
    public Vehiculo obtenerVehiculoPorId(Long id) {
        return vehiculoDao.findById(id).orElseThrow(() -> new RuntimeException("Vehiculo no encontrado"));
    }

    @Override
    public void actualizarVehiculo(Vehiculo vehiculo) {
        if (vehiculoDao.existsById(vehiculo.getId())) {
            vehiculoDao.save(vehiculo);
        } else {
            throw new RuntimeException("Vehiculo no encontrado");
        }
    }

    @Override
    public void eliminarVehiculo(Long id) {
        if (vehiculoDao.existsById(id)) {
            vehiculoDao.deleteById(id);
        } else {
            throw new RuntimeException("Vehiculo no encontrado");
        }
    }

    @Override
    public List<Vehiculo> obtenerVehiculosPorId(List<Long> ids) {
        return vehiculoDao.findAllById(ids);
    }
}
