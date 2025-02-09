package com.total.motors.store.service.impl;

import com.total.motors.store.dao.PedidoDao;
import com.total.motors.store.dao.PedidoProductoDao;
import com.total.motors.store.entity.*;
import com.total.motors.store.service.ClienteService;
import com.total.motors.store.service.PedidoService;
import com.total.motors.store.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoDao pedidoDao;
    private final PedidoProductoDao pedidoProductoDao;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoDao.findAll();
    }

    @Override
    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoDao.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Override
    public Pedido crearPedido(Long idCliente, Map<Long, Integer> productosSeleccionados, String usuarioRegistro) {
        Cliente cliente = clienteService.buscarClientePorId(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<Producto> productos = productoService.buscarProductosPorId(new ArrayList<>(productosSeleccionados.keySet()));

        BigDecimal subtotal = BigDecimal.ZERO;
        List<PedidoProducto> pedidoProductos = new ArrayList<>();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setImporte(BigDecimal.ZERO);
        pedido.setEstado(true);
        pedido.setFechaRegistro(LocalDateTime.now());
        pedido.setUsuarioRegistro(usuarioRegistro);
        pedido.setFechaActualizacion(LocalDateTime.now());
        pedido.setUsuarioActualizacion(usuarioRegistro);

        Pedido pedidoGuardado = pedidoDao.save(pedido); // Primero guardamos el pedido

        for (Producto producto : productos) {
            int cantidad = productosSeleccionados.getOrDefault(producto.getId(), 1);
            BigDecimal precioTotal = producto.getPrecioUnitario().multiply(BigDecimal.valueOf(cantidad));
            subtotal = subtotal.add(precioTotal);

            PedidoProducto pedidoProducto = new PedidoProducto();
            pedidoProducto.setId(new PedidoProductoId(pedidoGuardado.getId(), producto.getId()));
            pedidoProducto.setPedido(pedidoGuardado);
            pedidoProducto.setProducto(producto);
            pedidoProducto.setCantidad(cantidad);
            pedidoProducto.setPrecioUnitario(producto.getPrecioUnitario());

            pedidoProductos.add(pedidoProducto);
        }

        BigDecimal igv = subtotal.multiply(BigDecimal.valueOf(0.18));
        BigDecimal total = subtotal.add(igv);

        pedidoGuardado.setImporte(total);
        pedidoDao.save(pedidoGuardado); // 🔹 Actualizamos el importe del pedido

        pedidoProductoDao.saveAll(pedidoProductos); // 🔹 Guardamos `PedidoProducto`

        return pedidoGuardado;
    }
}
