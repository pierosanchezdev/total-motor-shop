package com.total.motors.store.service;

import com.total.motors.store.entity.Proveedor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProveedorService {
    Page<Proveedor> listarProveedoresPaginable(int page);
    List<Proveedor> listarProveedores();
    void crearProveedor(Proveedor proveedor);
    Proveedor obtenerProveedorPorId(Long id);
    void actualizarProveedor(Proveedor proveedor);
    void eliminarProveedor(Long id);
}
