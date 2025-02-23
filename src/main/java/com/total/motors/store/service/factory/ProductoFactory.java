package com.total.motors.store.service.factory;

import com.total.motors.store.dto.ProductoDTO;
import com.total.motors.store.dto.VehiculoDTO;
import com.total.motors.store.entity.Producto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductoFactory {

    public ProductoDTO productoAproductoDTO(Producto producto){
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precioUnitario(producto.getPrecioUnitario())
                .stock(producto.getStock())
                .categoria(producto.getCategoria().getNombreCategoria())
                .proveedor(producto.getProveedor().getNombre())
                .vehiculos(vehiculoAVehiculoDTO(producto))
                .build();
    }

    private List<VehiculoDTO> vehiculoAVehiculoDTO(Producto producto) {
        return producto.getVehiculos().stream()
                .map(vehiculo -> VehiculoDTO.builder()
                        .id(vehiculo.getId())
                        .marca(vehiculo.getMarca())
                        .version(vehiculo.getVersion())
                        .anio(vehiculo.getAnio())
                        .tipoCaja(vehiculo.getTipoCaja())
                        .build())
                .collect(Collectors.toList());
    }
}
