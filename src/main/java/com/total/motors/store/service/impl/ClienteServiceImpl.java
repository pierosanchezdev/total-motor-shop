package com.total.motors.store.service.impl;

import com.total.motors.store.dao.ClienteDao;
import com.total.motors.store.entity.Cliente;
import com.total.motors.store.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDao clienteDao;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    public Page<Cliente> listarClientesPaginable(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteDao.findAll();
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteDao.findById(id);
    }

}
