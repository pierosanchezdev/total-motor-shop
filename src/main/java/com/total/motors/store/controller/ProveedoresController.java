package com.total.motors.store.controller;

import com.total.motors.store.entity.Proveedor;
import com.total.motors.store.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("proveedores")
@RequiredArgsConstructor
public class ProveedoresController {

    private final ProveedorService proveedoresService;

    @GetMapping
    public String listarProveedores(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Proveedor> proveedores = proveedoresService.listarProveedoresPaginable(page);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", proveedores.getTotalPages());
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores";
    }

    @PostMapping("/crear")
    public String crearProveedor(@ModelAttribute Proveedor proveedor) {
        proveedoresService.crearProveedor(proveedor);
        return "redirect:/proveedores";
    }
}
