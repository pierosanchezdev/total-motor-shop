package com.total.motors.store.dao;

import com.total.motors.store.document.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDao extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByUsernameAndPassword(String username, String password);
    Optional<Usuario> findByUsername(String username);
}
