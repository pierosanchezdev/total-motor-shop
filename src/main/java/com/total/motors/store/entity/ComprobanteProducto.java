package com.total.motors.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "comprobante_producto")
@Getter
@Setter
@NoArgsConstructor
public class ComprobanteProducto {

    @EmbeddedId
    private ComprobanteProductoId id;

    @ManyToOne
    @JoinColumn(name = "id_comprobante", insertable = false, updatable = false)
    private ComprobantePago comprobantePago;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    private int cantidad;
    private BigDecimal precioUnitario;
}
