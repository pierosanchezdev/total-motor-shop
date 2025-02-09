package com.total.motors.store.dao;

import com.total.motors.store.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoDao extends JpaRepository<Vehiculo, Long> {
}
