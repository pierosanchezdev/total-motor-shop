package com.total.motors.store.controller;

import com.total.motors.store.entity.Categoria;
import com.total.motors.store.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Categoria> categorias = categoriaService.listarCategoriasPaginable(page);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("modo", "crear");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categorias.getTotalPages());
        return "categorias";
    }

    @PostMapping("/crear")
    public String crearCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.crearCategoria(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        model.addAttribute("categoria", categoria);
        model.addAttribute("modo", "editar");
        return "categorias";
    }

    @PostMapping("/editar")
    public String actualizarCategoria(@ModelAttribute Categoria categoria) {
        if (categoria.getId() == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para actualizar.");
        }
        Categoria existente = categoriaService.obtenerCategoriaPorId(categoria.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Categoria no encontrado.");
        }

        existente.setNombreCategoria(categoria.getNombreCategoria());
        existente.setDescripcion(categoria.getDescripcion());
        existente.setCategoriaPadre(categoria.getCategoriaPadre());
        existente.setEstado(categoria.isEstado());

        categoriaService.actualizarCategoria(existente);
        return "redirect:/categorias";
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<Categoria> verDetalleCategoria(@PathVariable Long id, Model model) {
        Categoria categoria =  categoriaService.obtenerCategoriaPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.eliminarCategoria(id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoria eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar al categoria porque existen productos relacionados a él.");
        }
        return "redirect:/categorias";
    }
}
