package com.sistema.zzootec.controllers;

import com.sistema.zzootec.models.Categoria;
import com.sistema.zzootec.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Page<Categoria> buscarListaCategorias(Pageable pageable) {
        return categoriaService.findAll(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Categoria buscarCategoriaPorId(@PathVariable("id") Long categoriaId) {
        return categoriaService.findById(categoriaId);
    }

    @GetMapping("/nombre/{nombre}")
    public Categoria buscarCategoriaPorNombre(@PathVariable("nombre") String nombreCategoria) {
        return categoriaService.findByNombre(nombreCategoria);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Categoria guardarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.saveCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria modificarCategoria(@PathVariable("id") Long categoriaId, @RequestBody Categoria categoria) {
        return categoriaService.updateCategoria(categoriaId, categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoriaPorId(@PathVariable("id") Long categoriaId) {
        categoriaService.deleteCategoriaById(categoriaId);
    }

}
