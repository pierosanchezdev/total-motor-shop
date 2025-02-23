package com.total.motors.store.controller;

import com.total.motors.store.dto.ProductoDTO;
import com.total.motors.store.dto.StockRequestDTO;
import com.total.motors.store.entity.*;
import com.total.motors.store.entity.Producto;
import com.total.motors.store.service.*;
import com.total.motors.store.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final ProveedorService proveedorService;
    private final VehiculoService vehiculoService;

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute Producto producto, @RequestParam List<Long> vehiculosSeleccionados) {
        if (vehiculosSeleccionados != null) {
            List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosPorId(vehiculosSeleccionados);
            producto.setVehiculos(vehiculos);
        }
        productoService.crearProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("proveedores", proveedorService.listarProveedores());
        model.addAttribute("productos", productoService.listarProductos());
        return "productos";
    }

    @PostMapping("/editar")
    public String actualizarProducto(@ModelAttribute Producto producto, @RequestParam List<Long> vehiculosSeleccionados) {
        if (producto.getId() == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para actualizar.");
        }
        Producto existente = productoService.obtenerProductoPorId(producto.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Producto no encontrado.");
        }
        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosPorId(vehiculosSeleccionados);
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecioUnitario(producto.getPrecioUnitario());
        existente.setStock(producto.getStock());
        existente.setCodigoBarras(producto.getCodigoBarras());
        existente.setCategoria(producto.getCategoria());
        existente.setProveedor(producto.getProveedor());
        existente.setVehiculos(vehiculos);
        productoService.actualizarProducto(existente);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productoService.eliminarProducto(id);
            redirectAttributes.addFlashAttribute("successMessage", "Producto eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar al producto porque existen productos relacionados a él.");
        }
        return "redirect:/productos";
    }

    @GetMapping
    public String listarProductos(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Producto> productos = productoService.listarProductosPaginable(page);
        List<Categoria> categorias = categoriaService.listarCategorias();
        List<Proveedor> proveedores = proveedorService.listarProveedores();
        List<Vehiculo> vehiculos = vehiculoService.listarVehiculos();
        model.addAttribute("productos", productos);
        model.addAttribute("producto", new Producto());
        model.addAttribute("modo", "crear");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productos.getTotalPages());
        model.addAttribute("categorias", categorias);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("producto", new Producto());
        return "productos";
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> verDetalleProducto(@PathVariable Long id, Model model) {
        ProductoDTO producto =  productoService.obtenerProductoDTOPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }
}
