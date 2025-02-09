package com.total.motors.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "plataforma_pago")
@Getter
@Setter
@NoArgsConstructor
public class PlataformaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;

    private String codigoTerminal;
    private BigDecimal importe;
    private String moneda;
    private String datosTarjeta;
    private LocalDateTime fechaPago;
    private String tipoPago;
    private int numeroCuotas;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
