package com.sistema.zzootec.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import com.sistema.zzootec.commons.JasperReportManager;
import com.sistema.zzootec.enums.TipoReporteEnum;
import com.sistema.zzootec.models.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;

@Service
public class ReporteServiceImpl implements ReporteService{

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public Reporte obtenerReporteVentas(String fileName, Map<String, Object> params)
            throws JRException, IOException, SQLException {
        Reporte dto = new Reporte();
                                    //PDF | EXCEL

        String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        //El id del parametro es enviado como string en postman, faltaria probar en angular, aunque se puede espicifar el tipo en typescript
        if (!Objects.isNull(params.get("ID")) && !params.get("ID").equals("")) {
            int valor = Integer.valueOf(params.get("ID").toString());
            params.replace("ID", valor);
        }

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;
    }

}
