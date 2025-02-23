package com.total.motors.store.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.total.motors.store.entity.Pedido;
import com.total.motors.store.service.ClienteService;
import com.total.motors.store.service.PedidoService;
import com.total.motors.store.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    @GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("productos", productoService.listarProductos());
        return "pedidos";
    }

    @PostMapping("/crear")
    public String crearPedido(@RequestParam("idCliente") Long idCliente,
                              @RequestParam("productosSeleccionados") String productosJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Long, Integer> productosSeleccionados;
        try {
            productosSeleccionados = objectMapper.readValue(productosJson, new TypeReference<Map<Long, Integer>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar productos", e);
        }

        pedidoService.crearPedido(idCliente, productosSeleccionados, "admin");

        return "redirect:/menu";
    }

    @GetMapping("/cobrar")
    public String listarPedidosPendientes(Model model) {
        List<Pedido> pedidosPendientes = pedidoService.listarPedidos();
        model.addAttribute("pedidosPendientes", pedidosPendientes);
        return "cobros";
    }
}
