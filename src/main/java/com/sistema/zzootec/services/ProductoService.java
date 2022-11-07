package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> findAll();
    public Producto findById(Long id);
    public Producto saveProducto(Producto producto);
    public Producto update(Long id, Producto producto);
    public void deleteById(Long id);
    public Producto findByNombre(String nombreProducto);
    public List<Producto> findProductsByCategoria(Long idCategoria);
    public List<Producto> findProductsRelacionados(Long idCategoria, Long idProducto);
}
