package com.total.motors.store.controller;

import com.total.motors.store.entity.Cliente;
import com.total.motors.store.entity.Cliente;
import com.total.motors.store.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public String listarClientes(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Cliente> clientes = clienteService.listarClientesPaginable(page);
        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("modo", "crear");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", clientes.getTotalPages());
        return "clientes";
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("modo", "editar");
        return "clientes";
    }

    @PostMapping("/editar")
    public String actualizarCliente(@ModelAttribute Cliente cliente) {
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para actualizar.");
        }
        Cliente existente = clienteService.obtenerClientePorId(cliente.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Cliente no encontrado.");
        }

        existente.setNombres(cliente.getNombres());
        existente.setApellidos(cliente.getApellidos());
        existente.setDireccion(cliente.getDireccion());
        existente.setTelefono(cliente.getTelefono());
        existente.setEmail(cliente.getEmail());
        existente.setEstado(cliente.isEstado());
        existente.setSegmento(cliente.getSegmento());
        existente.setLimiteCredito(cliente.getLimiteCredito());

        clienteService.actualizarCliente(existente);
        return "redirect:/clientes";
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> verDetalleCliente(@PathVariable Long id, Model model) {
        Cliente cliente =  clienteService.obtenerClientePorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            clienteService.eliminarCliente(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar al cliente porque existen productos relacionados a él.");
        }
        return "redirect:/clientes";
    }
}
