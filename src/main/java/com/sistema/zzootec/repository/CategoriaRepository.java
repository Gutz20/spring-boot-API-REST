package com.sistema.zzootec.repository;

import com.sistema.zzootec.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findByNombre(String nombreCategoria);
}
