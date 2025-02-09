package com.total.motors.store.service;

import com.total.motors.store.entity.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> listarCategorias();
    Page<Categoria> listarCategoriasPaginado(int page, int size);
    Categoria guardarCategoria(Categoria categoria);
    Optional<Categoria> obtenerCategoriaPorId(Long id);
    void eliminarCategoria(Long id);
}
