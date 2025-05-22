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
import java.time.LocalDate;
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
    private CicloRepository cicloRepository;
    @Autowired
    private RecetaRepository recetaRepository;
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public byte[] exportReportToPdf(String reportType) throws FileNotFoundException, JRException {
        return exportReportToPdf(reportType, null);
    }

    public byte[] exportReportToPdf(String reportType, Object parameter) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile("classpath:reports/" + reportType + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = getDataSourceForReport(reportType, parameter);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Sistema de Salud Reproductiva");

        if (parameter != null) {
            parameters.put("parametro", parameter);
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        return outputStream.toByteArray();
    }
    private JRBeanCollectionDataSource getDataSourceForReport(String reportType, Object parameter) {
        switch(reportType.toLowerCase()) {
            case "pacientes":
                return new JRBeanCollectionDataSource(pacienteRepository.findAllPacientes());
            case "medicos":
                return new JRBeanCollectionDataSource(medicoRepository.findAllMedicos());
            case "consultas":
                return new JRBeanCollectionDataSource(consultaRepository.findConsultasRecientes());
            case "consultas-medico":
                return new JRBeanCollectionDataSource(consultaRepository.findAllWithMedicoAndPaciente());
            case "examenes":
                return new JRBeanCollectionDataSource(examenRepository.findAllWithConsulta());
            case "ciclos":
                return new JRBeanCollectionDataSource(cicloRepository.findAllWithPaciente());
            case "estadisticas":
                return new JRBeanCollectionDataSource(consultaRepository.findEstadisticasPorEspecialidad());
            case "pacientes-completo":
                LocalDate fecha = parameter != null ? (LocalDate) parameter : LocalDate.now().minusMonths(6);
                return new JRBeanCollectionDataSource(pacienteRepository.findPacientesConConsultasRecientes(fecha));
           // case "medicos-top":
          //      return new JRBeanCollectionDataSource(medicoRepository.findMedicosConMasConsultasQueElPromedio());
            case "ciclos-irregulares":
                return new JRBeanCollectionDataSource(cicloRepository.findPacientesConCiclosIrregulares());
            default:
                throw new IllegalArgumentException("Tipo de reporte no soportado: " + reportType);
        }
    }
    
}