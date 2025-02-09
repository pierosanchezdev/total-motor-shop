package com.total.motors.store.controller;

import com.total.motors.store.entity.Vehiculo;
import com.total.motors.store.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @GetMapping
    public String listarVehiculos(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Vehiculo> vehiculos = vehiculoService.listarVehiculosPaginable(PageRequest.of(page, 10));
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vehiculos.getTotalPages());
        return "vehiculos";
    }

    @PostMapping("/crear")
    public String crearVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.guardarVehiculo(vehiculo);
        return "redirect:/vehiculos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
        return "redirect:/vehiculos";
    }
}
