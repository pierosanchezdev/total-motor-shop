package com.total.motors.store.service.impl;

import com.total.motors.store.dao.UsuarioDao;
import com.total.motors.store.document.Usuario;
import com.total.motors.store.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UsuarioDao usuarioDao;

    @Override
    public Optional<Usuario> authentication(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }
}
