package com.javatechie.jasper.report.service;

import com.javatechie.jasper.report.dao.OrderRepository;
import com.javatechie.jasper.report.entity.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.jasperreports.engine.JasperCompileManager.compileReport;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @PostConstruct
    public void initOrders() {
        List<Order> orders = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            orders.add(new Order(i, "25/10/2019", "shipName" + i, "region" + i, "code" + i));
        }
        repository.saveAll(orders);
    }

    public String generateReport() throws JRException, FileNotFoundException {
        List<Order> orders = repository.findAll();

        String reportPath = "C:\\Users\\Basant.Hota\\Desktop\\SCRIPT\\Jasper-Report";

        // Compile the Jasper report from .jrxml to .japser
        File file = ResourceUtils.getFile("classpath:orders.jrxml");
        JasperReport jasperReport = compileReport(file.getAbsolutePath());

        // Get your data source
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(orders, false);

        // Add parameters
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("createdBy", "javatechie.in");

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                jrBeanCollectionDataSource);

        // Export the report to a PDF and HTML file
        JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\orders.pdf");
        JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "\\orders.html");

        //Excel  parsing start
/*        JRXlsExporter exporter = new JRXlsExporter();
        SimpleXlsxReportConfiguration reportConfig
                = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[] { "orders data" });

        exporter.setConfiguration(reportConfig);
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath + "\\orders.xls"));
        exporter.exportReport();*/
        //Excel  parsing end
        System.out.println("Done");

        return "Report successfully generated @path= " + reportPath;

    }
}
