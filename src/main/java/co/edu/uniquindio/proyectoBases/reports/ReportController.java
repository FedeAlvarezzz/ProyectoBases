package co.edu.uniquindio.proyectoBases.reports;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/generar/{formato}/{tipo}")
    public String generarReporte(@PathVariable String formato, @PathVariable String tipo) throws FileNotFoundException, JRException {
        return reportService.exportReport(formato, tipo);
    }
}