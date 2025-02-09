package com.total.motors.store.service.impl;

import com.total.motors.store.dao.ProveedorDao;
import com.total.motors.store.entity.Proveedor;
import com.total.motors.store.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorDao proveedorDao;

    @Override
    public Page<Proveedor> listarProveedoresPaginable(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return proveedorDao.findAll(pageable);
    }

    @Override
    public List<Proveedor> listarProveedores() {
        return proveedorDao.findAll();
    }

    @Override
    public void crearProveedor(Proveedor proveedor) {
        proveedorDao.save(proveedor);
    }
}
