package com.total.motors.store.controller;

import com.total.motors.store.dto.ProductoDTO;
import com.total.motors.store.dto.StockRequestDTO;
import com.total.motors.store.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoRestController {

    private final ProductoService productoService;

    @PostMapping("/actualizar-stock")
    public ResponseEntity<?> actualizarStock(@RequestBody StockRequestDTO request) {
        boolean actualizado = productoService.reducirStock(request.getProductoId(), request.getCantidad());
        if (actualizado) {
            int nuevoStock = productoService.obtenerStock(request.getProductoId());
            return ResponseEntity.ok(Collections.singletonMap("nuevoStock", nuevoStock));
        } else {
            return ResponseEntity.badRequest().body("Stock insuficiente o producto no encontrado.");
        }
    }

    @PostMapping("/restaurar-stock")
    public ResponseEntity<?> restaurarStock(@RequestBody StockRequestDTO request) {
        if (request == null || request.getProductoId() == null || request.getCantidad() <= 0) {
            System.out.println("❌ Error: Datos de la petición vacíos o incorrectos.");
            return ResponseEntity.badRequest().body("Datos inválidos en la solicitud.");
        }

        System.out.println("🔄 Restaurando stock de producto ID: " + request.getProductoId() + ", cantidad: " + request.getCantidad());

        boolean restaurado = productoService.aumentarStock(request.getProductoId(), request.getCantidad());

        if (restaurado) {
            int nuevoStock = productoService.obtenerStock(request.getProductoId());
            System.out.println("✅ Stock restaurado. Nuevo stock: " + nuevoStock);
            return ResponseEntity.ok(Collections.singletonMap("nuevoStock", nuevoStock));
        } else {
            System.out.println("❌ Error al restaurar stock.");
            return ResponseEntity.badRequest().body("No se pudo restaurar el stock.");
        }
    }



}
