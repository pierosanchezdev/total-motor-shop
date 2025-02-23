package com.total.motors.store.data;

import com.total.motors.store.dao.UsuarioDao;
import com.total.motors.store.document.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInitialData implements CommandLineRunner {

    private final UsuarioDao usuarioDao;

    @Override
    public void run(String... args) throws Exception {
        if(usuarioDao.count() == 0){

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            Usuario admin = new Usuario("admin", encoder.encode("admin123"));
            Usuario user1 = new Usuario("user1", encoder.encode("password1"));
            Usuario user2 = new Usuario("user2", encoder.encode("password1"));

            usuarioDao.saveAll(List.of(admin,user1,user2));
            System.out.println("✅ Usuarios de prueba creados en MongoDB");
        } else {
            System.out.println("✅ Ya existen usuarios en la base de datos, no se crearon nuevos.");
        }
    }
}
