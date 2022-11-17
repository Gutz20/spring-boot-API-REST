package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Reporte;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface ReporteService {
    Reporte obtenerReporteVentas(String fileName, Map<String, Object> params) throws JRException, IOException, SQLException;
}
