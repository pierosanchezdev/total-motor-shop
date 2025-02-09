package com.total.motors.store.service;

import com.total.motors.store.entity.Proveedor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProveedorService {

    List<Proveedor> listarProveedores();
    Page<Proveedor> listarProveedoresPaginable(int page);
    void crearProveedor(Proveedor proveedor);
}
