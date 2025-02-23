package com.total.motors.store.controller;

import com.total.motors.store.entity.Vehiculo;
import com.total.motors.store.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @GetMapping
    public String listarVehiculos(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Vehiculo> vehiculos = vehiculoService.listarVehiculosPaginable(page);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("modo", "crear");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vehiculos.getTotalPages());
        return "vehiculos";
    }

    @PostMapping("/crear")
    public String crearVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.crearVehiculo(vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/editar/{id}")
    public String editarVehiculo(@PathVariable Long id, Model model) {
        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id);
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("modo", "editar");
        return "vehiculos";
    }

    @PostMapping("/editar")
    public String actualizarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        if (vehiculo.getId() == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para actualizar.");
        }
        Vehiculo existente = vehiculoService.obtenerVehiculoPorId(vehiculo.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Vehiculo no encontrado.");
        }

        existente.setMarca(vehiculo.getMarca());
        existente.setModelo(vehiculo.getModelo());
        existente.setAnio(vehiculo.getAnio());
        existente.setTipoCaja(vehiculo.getTipoCaja());
        existente.setVersion(vehiculo.getVersion());
        existente.setEstado(vehiculo.isEstado());

        vehiculoService.actualizarVehiculo(existente);
        return "redirect:/vehiculos";
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<Vehiculo> verDetalleVehiculo(@PathVariable Long id, Model model) {
        Vehiculo vehiculo =  vehiculoService.obtenerVehiculoPorId(id);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            vehiculoService.eliminarVehiculo(id);
            redirectAttributes.addFlashAttribute("successMessage", "Vehiculo eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar al vehiculo porque existen productos relacionados a él.");
        }
        return "redirect:/vehiculos";
    }
}
