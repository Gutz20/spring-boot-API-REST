package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Factura;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FacturaService {
    public List<Factura> findAll();
    public Factura save(Factura cliente);
    public Factura findById(Long id);
    public List<Factura> findOrdersByUsuarioId(Long id);
}
