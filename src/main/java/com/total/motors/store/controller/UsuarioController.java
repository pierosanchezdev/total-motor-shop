package com.total.motors.store.controller;

import com.total.motors.store.document.Usuario;
import com.total.motors.store.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Usuario> usuarios = usuarioService.listarUsuariosPaginable(page);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("modo", "crear");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usuarios.getTotalPages());
        return "usuarios";
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable String id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("modo", "editar");
        return "usuarios";
    }

    @PostMapping("/editar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario) {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para actualizar.");
        }
        Usuario existente = usuarioService.obtenerUsuarioPorId(usuario.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }

        existente.setNombres(usuario.getNombres());
        existente.setApellidos(usuario.getApellidos());
        existente.setDireccion(usuario.getDireccion());
        existente.setTelefono(usuario.getTelefono());
        existente.setEmail(usuario.getEmail());
        existente.setEstado(usuario.isEstado());
        existente.setUsername(usuario.getUsername());
        existente.setPassword(usuario.getPassword());

        usuarioService.actualizarUsuario(existente);
        return "redirect:/usuarios";
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> verDetalleUsuario(@PathVariable String id, Model model) {
        Usuario usuario =  usuarioService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.eliminarUsuario(id);
            redirectAttributes.addFlashAttribute("successMessage", "Usuario eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar al usuario porque existen productos relacionados a él.");
        }
        return "redirect:/usuarios";
    }
}
