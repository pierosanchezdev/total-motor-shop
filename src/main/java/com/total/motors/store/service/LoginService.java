package com.total.motors.store.service;

import com.total.motors.store.document.Usuario;

import java.util.Optional;

public interface LoginService {

    Optional<Usuario> authentication(String username, String password);
}
