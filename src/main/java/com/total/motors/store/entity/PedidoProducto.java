package com.total.motors.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido_producto")
@Getter
@Setter
@NoArgsConstructor
public class PedidoProducto {

    @EmbeddedId
    private PedidoProductoId id;

    @ManyToOne
    @JoinColumn(name = "id_pedido",insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    private int cantidad;
    private BigDecimal precioUnitario;
}
