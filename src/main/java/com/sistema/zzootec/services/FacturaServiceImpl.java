package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Factura;
import com.sistema.zzootec.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Factura> findOrdersByUsuarioId(Long id) {
        return facturaRepository.buscarOrdenesPorIdDeUsuario(id);
    }
}
