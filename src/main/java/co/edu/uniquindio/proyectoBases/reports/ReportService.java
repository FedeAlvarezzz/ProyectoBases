package co.edu.uniquindio.proyectoBases.reports;

import co.edu.uniquindio.proyectoBases.repositories.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
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

    public byte[] exportReportToPdf(String reportType) throws FileNotFoundException, JRException {
        // Cargar el archivo .jrxml
        File file = ResourceUtils.getFile("classpath:reports/" + reportType + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Obtener datos según el tipo de reporte
        JRBeanCollectionDataSource dataSource = getDataSourceForReport(reportType);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Sistema de Salud Reproductiva");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF en memoria
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        return outputStream.toByteArray();
    }

    private JRBeanCollectionDataSource getDataSourceForReport(String reportType) {
        switch(reportType.toLowerCase()) {
            case "pacientes":
                return new JRBeanCollectionDataSource(pacienteRepository.findAll());
            case "consultas":
                return new JRBeanCollectionDataSource(consultaRepository.findAll());
            case "consultas-medico":
                return new JRBeanCollectionDataSource(consultaRepository.findAllWithMedico());
            case "examenes":
                return new JRBeanCollectionDataSource(examenRepository.findAll());
        //    case "pacientes-completo":
       //         return new JRBeanCollectionDataSource(pacienteRepository.findAllWithHistorial());
            // Agrega más casos según necesites
            default:
                throw new IllegalArgumentException("Tipo de reporte no soportado: " + reportType);
        }
    }
}