package com.total.motors.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ComprobanteProductoId implements Serializable {

    @Column(name = "id_comprobante")
    private Long idComprobante;

    @Column(name = "id_producto")
    private Long idProducto;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ComprobanteProductoId that = (ComprobanteProductoId) obj;
        return Objects.equals(idComprobante, that.idComprobante) && Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComprobante, idProducto);
    }
}
