package com.total.motors.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioUnitario;
    private Integer stock;
    private String categoria;
    private String codigoBarras;
    private String proveedor;
    private List<VehiculoDTO> vehiculos;
}
