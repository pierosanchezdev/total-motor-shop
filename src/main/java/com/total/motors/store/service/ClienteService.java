package com.total.motors.store.service;

import com.total.motors.store.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente crearCliente(Cliente cliente);

    Page<Cliente> listarClientesPaginable(Pageable pageable);

    List<Cliente> listarClientes();

    Optional<Cliente> buscarClientePorId(Long id);
}
