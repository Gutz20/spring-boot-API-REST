package com.sistema.zzootec.controllers;

import com.sistema.zzootec.models.ImageModel;
import com.sistema.zzootec.models.Producto;
import com.sistema.zzootec.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Producto> buscarListaProductos() {
        return productoService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Producto buscarProductoPorId(@PathVariable("id") Long productoId) {
        return productoService.findById(productoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{name}")
    public Producto buscarProductoPorNombre(@PathVariable("name") String nombreProducto) {
        return productoService.findByNombre(nombreProducto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categoria/{idCategoria}")
    public List<Producto> buscarProductosPorCategoria(@PathVariable("idCategoria") Long categoriaId) {
        return productoService.findProductsByCategoria(categoriaId);
    }

    @GetMapping("{idProducto}/categoria/{idCategoria}")
    public List<Producto> buscarProductosRelacionados(@PathVariable("idProducto") Long idProducto, @PathVariable("idCategoria") Long idCategoria) {
        return productoService.findProductsRelacionados(idCategoria, idProducto);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public Producto guardarProducto(@RequestPart("producto") Producto producto,
                                    @RequestPart("imageFile")MultipartFile[] file) {
        try {
            Set<ImageModel> images = uploadImage(file);
            producto.setImagenes(images);
            return productoService.saveProducto(producto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Producto editarProducto(@PathVariable("id") Long productoId, @RequestPart("producto") Producto producto,
                                                                        @RequestPart("imageFile")MultipartFile[] file){
        try {
            Set<ImageModel> images = uploadImage(file);
            producto.setImagenes(images);
            return productoService.update(productoId, producto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void eliminarProductoPorId(@PathVariable("id") Long id) {
        productoService.deleteById(id);
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageModel  imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }

}
