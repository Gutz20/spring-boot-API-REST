package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {
    public Page<Categoria> findAll(Pageable pageable);
    public Categoria findById(Long id);
    public Categoria findByNombre(String nombreCategoria);
    public Categoria saveCategoria(Categoria categoria);
    void deleteCategoriaById(Long categoriaId);
    Categoria updateCategoria(Long categoriaId, Categoria categoria);

}
