package com.total.motors.store.controller;

import com.total.motors.store.entity.Categoria;
import com.total.motors.store.entity.Producto;
import com.total.motors.store.entity.Proveedor;
import com.total.motors.store.entity.Vehiculo;
import com.total.motors.store.service.CategoriaService;
import com.total.motors.store.service.ProductoService;
import com.total.motors.store.service.ProveedorService;
import com.total.motors.store.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final ProveedorService proveedorService;
    private final VehiculoService vehiculoService;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.listarProductos();
        List<Categoria> categorias = categoriaService.listarCategorias();
        List<Proveedor> proveedores = proveedorService.listarProveedores();
        List<Vehiculo> vehiculos = vehiculoService.listarVehiculos();

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("producto", new Producto());

        return "productos";
    }

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute Producto producto, @RequestParam List<Long> vehiculosSeleccionados) {
        if (vehiculosSeleccionados != null) {
            List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosPorId(vehiculosSeleccionados);
            producto.setVehiculos(vehiculos);
        }
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            model.addAttribute("categorias", categoriaService.listarCategorias());
            model.addAttribute("proveedores", proveedorService.listarProveedores());
            model.addAttribute("vehiculos", vehiculoService.listarVehiculos());
            return "productos";
        } else {
            return "redirect:/productos";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto, @RequestParam List<Long> vehiculosSeleccionados) {
        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosPorId(vehiculosSeleccionados);
        producto.setVehiculos(vehiculos);
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}
