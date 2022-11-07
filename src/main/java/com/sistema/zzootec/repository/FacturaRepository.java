package com.sistema.zzootec.repository;

import com.sistema.zzootec.models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    @Query(value = "SELECT * FROM Facturas WHERE usuario_id = :id", nativeQuery = true)
    public List<Factura> buscarOrdenesPorIdDeUsuario(@Param("id") Long id);
}
