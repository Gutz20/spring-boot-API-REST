package com.sistema.zzootec.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "categorias",
        uniqueConstraints = @UniqueConstraint(columnNames = "nombre")
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "productos")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    @NotNull
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore()
    private Set<Producto> productos = new HashSet<>();
}
