package com.javatechie.jasper.report;

import com.javatechie.jasper.report.service.OrderService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@SpringBootApplication
@RestController
public class SpringBootJasperReportApplication {

    @Autowired
    private OrderService service;

    @GetMapping("/reports")
    public String exportReport() throws JRException, FileNotFoundException {
        return service.generateReport();
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringBootJasperReportApplication.class, args);
    }

}
