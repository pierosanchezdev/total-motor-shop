package com.total.motors.store.service.impl;

import com.total.motors.store.dao.ClienteDao;
import com.total.motors.store.entity.Cliente;
import com.total.motors.store.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDao clienteDao;

    @Override
    public Page<Cliente> listarClientesPaginable(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return clienteDao.findAll(pageable);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteDao.findAll();
    }

    @Override
    public void crearCliente(Cliente usuario) {
        clienteDao.save(usuario);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteDao.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public void actualizarCliente(Cliente usuario) {
        if (clienteDao.existsById(usuario.getId())) {
            clienteDao.save(usuario);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        if (clienteDao.existsById(id)) {
            clienteDao.deleteById(id);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @Override
    public List<Cliente> obtenerClientesPorId(List<Long> ids) {
        return clienteDao.findAllById(ids);
    }

}
