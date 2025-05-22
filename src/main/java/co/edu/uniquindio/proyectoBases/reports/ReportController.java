package co.edu.uniquindio.proyectoBases.reports;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/reportes")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public String mostrarPaginaReportes() {
        return "reportes/reportes"; // Esto apunta a templates/reportes/reportes.html
    }

    @GetMapping("/generar/{tipo}")
    public ResponseEntity<InputStreamResource> generarReporte(@PathVariable String tipo) throws FileNotFoundException, JRException {
        byte[] reporte = reportService.exportReportToPdf(tipo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + tipo + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(reporte)));
    }
}