package com.sistema.zzootec.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image_model")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageModel {

    public ImageModel(String nombre, String tipo, byte[] picByte) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.picByte = picByte;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;
    @Column(length = 50000000)
    private byte[] picByte;

}
