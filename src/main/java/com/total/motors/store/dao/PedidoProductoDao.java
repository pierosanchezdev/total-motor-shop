package com.total.motors.store.dao;

import com.total.motors.store.entity.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProductoDao extends JpaRepository<PedidoProducto, Long> {
}
