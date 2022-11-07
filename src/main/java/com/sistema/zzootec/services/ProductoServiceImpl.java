package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Producto;
import com.sistema.zzootec.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Long id, Producto producto) {
        Producto productoDB = productoRepository.findById(id).get();

        if (Objects.nonNull(producto.getNombre()) &&
        !"".equalsIgnoreCase(producto.getNombre())) {
            productoDB.setNombre(producto.getNombre());
        }

        if (Objects.nonNull(producto.getDescripcion()) &&
                !"".equalsIgnoreCase(producto.getDescripcion())) {
            productoDB.setDescripcion(producto.getDescripcion());
        }

        if (Objects.nonNull(producto.getPrecio())) {
            productoDB.setPrecio(producto.getPrecio());
        }

        if (Objects.nonNull(producto.getCategoria())) {
            productoDB.setCategoria(producto.getCategoria());
        }

        if (Objects.nonNull(producto.getCantidad()) &&
                !"".equalsIgnoreCase(producto.getCantidad().toString())) {
            productoDB.setCantidad(producto.getCantidad());
        }

        productoDB.setImagenes(producto.getImagenes());

        return productoRepository.save(productoDB);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto findByNombre(String nombreProducto) {
        return productoRepository.findByNombre(nombreProducto);
    }

    @Override
    public List<Producto> findProductsByCategoria(Long idCategoria) {
        return productoRepository.findProductsByCategoria(idCategoria);
    }

    @Override
    public List<Producto> findProductsRelacionados(Long idCategoria, Long idProducto) {
        return productoRepository.findProductsRelacionados(idCategoria, idProducto);
    }

}
