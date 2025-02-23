package com.total.motors.store.service.impl;

import com.total.motors.store.dao.UsuarioDao;
import com.total.motors.store.document.Usuario;
import com.total.motors.store.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    
    private final UsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<Usuario> listarUsuariosPaginable(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return usuarioDao.findAll(pageable);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioDao.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(String id) {
        return usuarioDao.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        if (usuarioDao.existsById(usuario.getId())) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioDao.save(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public void eliminarUsuario(String id) {
        if (usuarioDao.existsById(id)) {
            usuarioDao.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public List<Usuario> obtenerUsuariosPorId(List<String> ids) {
        return usuarioDao.findAllById(ids);
    }
}
