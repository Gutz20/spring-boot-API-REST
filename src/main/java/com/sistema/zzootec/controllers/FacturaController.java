package com.sistema.zzootec.controllers;

import com.sistema.zzootec.models.Factura;
import com.sistema.zzootec.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Factura> getAll() {
        return facturaService.findAll();
    }

    @GetMapping("/{id}")
    public Factura getFacturaById(@PathVariable("id") Long facturaId) {
        return facturaService.findById(facturaId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Factura create(@RequestBody Factura factura) {
        return facturaService.save(factura);
    }

    @GetMapping("/usuario/{id}")
    public List<Factura> getAllOrderByUserId(@PathVariable("id") Long id) {
        return facturaService.findOrdersByUsuarioId(id);
    }

}
