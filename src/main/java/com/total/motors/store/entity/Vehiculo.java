package com.total.motors.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vehiculo")
@Getter
@Setter
@NoArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private int anio;
    private String tipoCaja;
    private String version;

    @ManyToMany(mappedBy = "vehiculos")
    @JsonIgnore
    private List<Producto> productos;

    private boolean estado;
    private LocalDateTime fechaRegistro;
    private String usuarioRegistro;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;
}
