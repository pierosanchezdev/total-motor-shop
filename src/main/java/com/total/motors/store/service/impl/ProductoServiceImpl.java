package com.total.motors.store.service.impl;

import com.total.motors.store.dao.ProductoDao;
import com.total.motors.store.dto.ProductoDTO;
import com.total.motors.store.entity.Producto;
import com.total.motors.store.entity.Producto;
import com.total.motors.store.service.ProductoService;
import com.total.motors.store.service.factory.ProductoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDao productoDao;
    private final ProductoFactory productoFactory;

    @Override
    public Page<Producto> listarProductosPaginable(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return productoDao.findAll(pageable);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoDao.findAll();
    }

    @Override
    public void crearProducto(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO obtenerProductoDTOPorId(Long id) {
        Producto producto = productoDao.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoFactory.productoAproductoDTO(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProductoPorId(Long id) {
        return productoDao.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public void actualizarProducto(Producto producto) {
        if (productoDao.existsById(producto.getId())) {
            productoDao.save(producto);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    @Override
    public void eliminarProducto(Long id) {
        if (productoDao.existsById(id)) {
            productoDao.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    @Override
    public List<Producto> obtenerProductosPorId(List<Long> ids) {
        return productoDao.findAllById(ids);
    }

    @Override
    public boolean reducirStock(Long idProducto, int cantidad) {
        Optional<Producto> optionalProducto = productoDao.findById(idProducto);

        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoDao.save(producto);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean aumentarStock(Long idProducto, int cantidad) {
        Optional<Producto> optionalProducto = productoDao.findById(idProducto);

        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            int nuevoStock = producto.getStock() + cantidad;
            producto.setStock(nuevoStock);
            productoDao.save(producto); // ✅ Guardar el nuevo stock en la BD
            System.out.println("✅ Stock actualizado en la BD: " + nuevoStock);
            return true;
        }
        System.out.println("❌ No se encontró el producto para restaurar stock.");
        return false;
    }

    @Override
    public int obtenerStock(Long idProducto) {
        return productoDao.findById(idProducto)
                .map(Producto::getStock)
                .orElse(0);
    }
}
