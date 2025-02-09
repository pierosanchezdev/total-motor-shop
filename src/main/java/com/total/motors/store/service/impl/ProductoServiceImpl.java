package com.total.motors.store.service.impl;

import com.total.motors.store.dao.ProductoDao;
import com.total.motors.store.entity.Producto;
import com.total.motors.store.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDao productoDao;

    @Override
    public Page<Producto> listarProductosPaginable(int page, int size) {
        return productoDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Producto> listarProductos() {
        return productoDao.findAll();
    }

    @Override
    public List<Producto> buscarProductosPorId(List<Long> ids) {
        return productoDao.findAllById(ids);
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoDao.findById(id);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoDao.deleteById(id);
    }
}
