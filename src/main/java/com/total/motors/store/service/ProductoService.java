package com.total.motors.store.service;

import com.total.motors.store.dto.ProductoDTO;
import com.total.motors.store.entity.Producto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductoService {
    Page<Producto> listarProductosPaginable(int page);
    List<Producto> listarProductos();
    void crearProducto(Producto Producto);
    Producto obtenerProductoPorId(Long id);
    ProductoDTO obtenerProductoDTOPorId(Long id);
    void actualizarProducto(Producto Producto);
    void eliminarProducto(Long id);
    List<Producto> obtenerProductosPorId(List<Long> ids);
    boolean reducirStock(Long idProducto, int cantidad);
    int obtenerStock(Long idProducto);
    boolean aumentarStock(Long idProducto, int cantidad);
}
