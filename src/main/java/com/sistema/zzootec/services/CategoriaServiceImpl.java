package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Categoria;
import com.sistema.zzootec.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).get();
    }

    @Override
    public Categoria findByNombre(String nombreCategoria) {
        return categoriaRepository.findByNombre(nombreCategoria);
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategoriaById(Long categoriaId) {
        categoriaRepository.deleteById(categoriaId);
    }

    @Override
    public Categoria updateCategoria(Long categoriaId, Categoria categoria) {
        Categoria categoriaDB = categoriaRepository.findById(categoriaId).get();

        if (Objects.nonNull(categoria.getNombre()) &&
        !"".equalsIgnoreCase(categoria.getNombre())) {
            categoriaDB.setNombre(categoria.getNombre());
        }

        if (Objects.nonNull(categoria.getDescripcion()) &&
                !"".equalsIgnoreCase(categoria.getDescripcion())) {
            categoriaDB.setDescripcion(categoria.getDescripcion());
        }

        return categoriaRepository.save(categoriaDB);
    }

}
