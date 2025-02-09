package com.total.motors.store.entity;

import com.total.motors.store.util.enums.FormaPago;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "comprobante_pago")
@Getter
@Setter
@NoArgsConstructor
public class ComprobantePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tipoDocumento;
    @Column(unique = true)
    private String numeroDocumento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private BigDecimal montoBase;
    private BigDecimal igv;
    private BigDecimal montoTotal;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "id_plataforma_pago", nullable = true)
    private PlataformaPago plataformaPago;

    @ManyToMany
    @JoinTable(
            name = "comprobante_producto",
            joinColumns = @JoinColumn(name = "id_comprobante"),
            inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    private List<Producto> productos;

    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;
}
