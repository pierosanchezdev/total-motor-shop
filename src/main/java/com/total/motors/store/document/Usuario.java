package com.total.motors.store.document;

import com.total.motors.store.entity.Persona;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Persona {

    @Id
    private String id;

    private String username;
    private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
