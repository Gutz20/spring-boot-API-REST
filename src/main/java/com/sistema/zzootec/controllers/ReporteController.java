package com.sistema.zzootec.controllers;

import com.sistema.zzootec.enums.TipoReporteEnum;
import com.sistema.zzootec.models.Reporte;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.zzootec.services.ReporteService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin("*")
public class ReporteController {
    @Autowired
    private ReporteService reporteVentasServiceAPI;

    @GetMapping(path = "/download/{fileName}")
    public ResponseEntity<Resource> reporteProductos(@PathVariable("fileName") String fileName, @RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        return generarReporte(fileName, params);
    }

    public ResponseEntity<Resource> generarReporte(String fileName, Map<String, Object> params) throws JRException, SQLException, IOException {
        Reporte dto = reporteVentasServiceAPI.obtenerReporteVentas(fileName, params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.valueOf("application/vnd.ms-excel");
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }
}
