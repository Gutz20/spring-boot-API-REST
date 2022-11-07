package com.sistema.zzootec.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "productos",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String nombre;
    @Column(length = 2000)
    private String descripcion;
    private Double precio;
    private Integer cantidad;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "producto_imagenes",
            joinColumns =  {
                @JoinColumn(name = "producto_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "imagen_id")
            }
    )
    private Set<ImageModel> imagenes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "producto")
    @JsonIgnore
    private List<ItemFactura> itemFacturas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
    }
}
