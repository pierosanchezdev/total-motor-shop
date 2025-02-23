package com.total.motors.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VehiculoDTO {
    private Long id;
    private String modelo;
    private String marca;
    private int anio;
    private String tipoCaja;
    private String version;
}
