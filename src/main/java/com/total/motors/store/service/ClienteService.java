package com.total.motors.store.service;

import com.total.motors.store.entity.Cliente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClienteService {
    Page<Cliente> listarClientesPaginable(int page);
    List<Cliente> listarClientes();
    void crearCliente(Cliente Cliente);
    Cliente obtenerClientePorId(Long id);
    void actualizarCliente(Cliente Cliente);
    void eliminarCliente(Long id);
    List<Cliente> obtenerClientesPorId(List<Long> ids);
}
