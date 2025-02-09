package com.total.motors.store.controller;

import com.total.motors.store.entity.Categoria;
import com.total.motors.store.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   Model model) {
        Page<Categoria> categorias = categoriaService.listarCategoriasPaginado(page, size);
        model.addAttribute("categorias", categorias.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categorias.getTotalPages());
        return "categorias";
    }

    @PostMapping("/crear")
    public String crearCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias";
    }
}
