package com.total.motors.store.controller;

import com.total.motors.store.entity.Proveedor;
import com.total.motors.store.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("proveedores")
@RequiredArgsConstructor
public class ProveedoresController {

    private final ProveedorService proveedoresService;

    @GetMapping
    public String listarProveedores(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Proveedor> proveedores = proveedoresService.listarProveedoresPaginable(page);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("modo", "crear");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", proveedores.getTotalPages());
        return "proveedores";
    }

    @PostMapping("/crear")
    public String crearProveedor(@ModelAttribute Proveedor proveedor) {
        proveedoresService.crearProveedor(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable Long id, Model model) {
        Proveedor proveedor = proveedoresService.obtenerProveedorPorId(id);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("modo", "editar");
        return "proveedores";
    }

    @PostMapping("/editar")
    public String actualizarProveedor(@ModelAttribute Proveedor proveedor) {
        if (proveedor.getId() == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para actualizar.");
        }
        Proveedor existente = proveedoresService.obtenerProveedorPorId(proveedor.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Proveedor no encontrado.");
        }

        existente.setNombre(proveedor.getNombre());
        existente.setDireccion(proveedor.getDireccion());
        existente.setTelefono(proveedor.getTelefono());
        existente.setRuc(proveedor.getRuc());
        existente.setEmail(proveedor.getEmail());
        existente.setNombreContacto(proveedor.getNombreContacto());
        existente.setEstado(proveedor.isEstado());

        proveedoresService.actualizarProveedor(existente);
        return "redirect:/proveedores";
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<Proveedor> verDetalleProveedor(@PathVariable Long id, Model model) {
        Proveedor proveedor =  proveedoresService.obtenerProveedorPorId(id);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            proveedoresService.eliminarProveedor(id);
            redirectAttributes.addFlashAttribute("successMessage", "Proveedor eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar al proveedor porque existen productos relacionados a él.");
        }
        return "redirect:/proveedores";
    }
}
