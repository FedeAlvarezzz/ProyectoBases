package co.edu.uniquindio.proyectoBases.reports;

import co.edu.uniquindio.proyectoBases.repositories.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepo pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private MetodoAnticonceptivoRepository metodoAnticonceptivoRepository;

    @Autowired
    private CicloRepository cicloRepository;

    public String exportReport(String reportFormat, String reportType) throws FileNotFoundException, JRException {
        String path = "src/main/resources/static/reports";

        // Cargar el archivo .jrxml
        File file = ResourceUtils.getFile("classpath:reports/" + reportType + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Obtener datos según el tipo de reporte
        JRBeanCollectionDataSource dataSource = getDataSourceForReport(reportType);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Sistema de Salud Reproductiva");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/" + reportType + ".html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/" + reportType + ".pdf");
        }

        return "Reporte generado en: " + path + "/" + reportType + "." + reportFormat;
    }

    private JRBeanCollectionDataSource getDataSourceForReport(String reportType) {
        switch(reportType) {
            case "pacientes":
                return new JRBeanCollectionDataSource(pacienteRepository.findAll());
            case "consultas":
                return new JRBeanCollectionDataSource(consultaRepository.findAll());
            case "examenes":
                return new JRBeanCollectionDataSource(examenRepository.findAll());
            // Agrega más casos según tus reportes
            default:
                return new JRBeanCollectionDataSource(pacienteRepository.findAll());
        }
    }
}