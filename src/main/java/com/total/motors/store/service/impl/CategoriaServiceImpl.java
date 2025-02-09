package com.total.motors.store.service.impl;

import com.total.motors.store.dao.CategoriaDao;
import com.total.motors.store.entity.Categoria;
import com.total.motors.store.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDao categoriaDao;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaDao.findAll();
    }

    @Override
    public Page<Categoria> listarCategoriasPaginado(int page, int size) {
        return categoriaDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaDao.save(categoria);
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaDao.findById(id);
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaDao.deleteById(id);
    }
}
