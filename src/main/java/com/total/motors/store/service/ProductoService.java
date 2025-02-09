package com.total.motors.store.service;

import com.total.motors.store.entity.Producto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Page<Producto> listarProductosPaginable(int page, int size);
    List<Producto> listarProductos();
    List<Producto> buscarProductosPorId(List<Long> ids);
    Optional<Producto> obtenerProductoPorId(Long id);
    Producto guardarProducto(Producto producto);
    void eliminarProducto(Long id);

}
