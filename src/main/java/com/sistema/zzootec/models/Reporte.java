package com.sistema.zzootec.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {
    private String fileName;
    private ByteArrayInputStream stream;
    private int length;
}
