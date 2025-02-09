package com.total.motors.store.service;

import com.total.motors.store.entity.Pedido;

import java.util.List;
import java.util.Map;

public interface PedidoService {

    List<Pedido> listarPedidos();
    Pedido obtenerPedidoPorId(Long id);
    Pedido crearPedido(Long idCliente, Map<Long, Integer> productosSeleccionados, String usuarioRegistro);
}
