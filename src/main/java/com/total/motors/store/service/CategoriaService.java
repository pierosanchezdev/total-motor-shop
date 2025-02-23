package com.total.motors.store.service;

import com.total.motors.store.entity.Categoria;
import com.total.motors.store.entity.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    Page<Categoria> listarCategoriasPaginable(int page);
    List<Categoria> listarCategorias();
    void crearCategoria(Categoria Categoria);
    Categoria obtenerCategoriaPorId(Long id);
    void actualizarCategoria(Categoria Categoria);
    void eliminarCategoria(Long id);
    List<Categoria> obtenerCategoriasPorId(List<Long> ids);
}
