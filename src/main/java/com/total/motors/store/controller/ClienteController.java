package com.total.motors.store.controller;

import com.total.motors.store.entity.Cliente;
import com.total.motors.store.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public String clientes(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           Model model){
        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clientes = clienteService.listarClientesPaginable(pageable);

        model.addAttribute("clientes", clientes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", clientes.getTotalPages());

        return "clientes";
    }

    @PostMapping("crear")
    public String crearCliente(@ModelAttribute Cliente cliente, Model model){
        clienteService.crearCliente(cliente);
        return "redirect:/clientes";
    }
}
