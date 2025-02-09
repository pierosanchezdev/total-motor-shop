package com.total.motors.store.dao;

import com.total.motors.store.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorDao extends JpaRepository<Proveedor, Long> {
}
