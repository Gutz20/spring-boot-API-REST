package com.sistema.zzootec.repository;

import com.sistema.zzootec.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    public Producto findByNombre(String nombreProducto);
    @Query(value = "SELECT * FROM Productos WHERE categoria_id = :id", nativeQuery = true)
    public List<Producto> findProductsByCategoria(@Param("id") Long idCategoria);
    @Query(value = "SELECT * FROM Productos WHERE categoria_id = :idCategoria AND producto_id != :idProducto", nativeQuery = true)
    public List<Producto> findProductsRelacionados(@Param("idCategoria") Long idCategoria, @Param("idProducto") Long idProducto );
}
