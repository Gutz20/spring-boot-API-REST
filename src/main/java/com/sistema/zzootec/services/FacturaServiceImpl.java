package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Factura;
import com.sistema.zzootec.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

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
