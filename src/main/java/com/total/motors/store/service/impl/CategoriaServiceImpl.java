package com.total.motors.store.service.impl;

import com.total.motors.store.dao.CategoriaDao;
import com.total.motors.store.entity.Categoria;
import com.total.motors.store.entity.Categoria;
import com.total.motors.store.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDao categoriaDao;

    @Override
    public Page<Categoria> listarCategoriasPaginable(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return categoriaDao.findAll(pageable);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaDao.findAll();
    }

    @Override
    public void crearCategoria(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaDao.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
        if (categoriaDao.existsById(categoria.getId())) {
            categoriaDao.save(categoria);
        } else {
            throw new RuntimeException("Categoria no encontrado");
        }
    }

    @Override
    public void eliminarCategoria(Long id) {
        if (categoriaDao.existsById(id)) {
            categoriaDao.deleteById(id);
        } else {
            throw new RuntimeException("Categoria no encontrado");
        }
    }

    @Override
    public List<Categoria> obtenerCategoriasPorId(List<Long> ids) {
        return categoriaDao.findAllById(ids);
    }
}
