package com.total.motors.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Persona {
    private String nombres;
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    private String email;
    private boolean estado;
    private LocalDateTime fechaRegistro;
    private String usuarioRegistro;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;
}
