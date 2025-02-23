package com.total.motors.store.service;

import com.total.motors.store.document.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {
    Page<Usuario> listarUsuariosPaginable(int page);
    List<Usuario> listarUsuarios();
    void crearUsuario(Usuario Usuario);
    Usuario obtenerUsuarioPorId(String id);
    void actualizarUsuario(Usuario Usuario);
    void eliminarUsuario(String id);
    List<Usuario> obtenerUsuariosPorId(List<String> ids);
}
