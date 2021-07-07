package com.gnp.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gnp.generator.domain.model.LineaAzulTemplate;
import com.gnp.generator.domain.model.LineaAzulTemplate.EffectiveDate;
import com.gnp.generator.domain.model.LineaAzulTemplate.Header;
import com.gnp.generator.domain.model.LineaAzulTemplate.Plan;
import com.gnp.generator.domain.model.LineaAzulTemplate.Total;
import com.gnp.generator.domain.repository.LineaAzulReportRepository;
import com.gnp.generator.infraestructura.repository.LineaAzulReportExcelRepository;

//@SpringBootApplication
public class TemplateEngineApplication {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(TemplateEngineApplication.class, args);
		LineaAzulReportRepository repository = new LineaAzulReportExcelRepository();
		LineaAzulTemplate template = new LineaAzulTemplate();
		template.setHeader(new Header("PATITO CONSULTORES,SC", "Renovación", "ARGU-XX", "XX",
				new EffectiveDate("2021-07-06", "2021-07-26")));

		template.setPlanes(Arrays.asList(
				new Plan("Premier 100", "0004 Omnia", "0004 Omnia", "Amparada", "No Amparada",
						new Total(1500000, "MXN"), new Total(2500, "MXN"), "10%", new Total(30000, "MXN")),
				new Plan("Premier 200", "0004 Omnia", "0004 Omnia", "Amparada", "No Amparada", new Total(1200, "SMGM"),
						new Total(3, "SMGM"), "10%", new Total(30000, "MXN")),
				new Plan("Premier 300", "0004 Omnia", "0004 Omnia", "Amparada", "No Amparada", new Total(1800, "SMGM"),
						new Total(2, "SMGM"), "10%", new Total(20000, "MXN")),
				new Plan("Premier 400", "0004 Omnia", "0004 Omnia", "Amparada", "No Amparada", new Total(1200, "SMGM"),
						new Total(3, "SMGM"), "10%", new Total(30000, "MXN")),
				new Plan("Premier 500", "0004 Omnia", "0004 Omnia", "Amparada", "No Amparada", new Total(1800, "SMGM"),
						new Total(2, "SMGM"), "10%", new Total(20000, "MXN")),
				new Plan("Premier 600", "0004 Omnia", "0004 Omnia", "Amparada", "No Amparada", new Total(1800, "SMGM"),
						new Total(2, "SMGM"), "10%", new Total(20000, "MXN"))));

		byte[] data = repository.generate(template);

		try (FileOutputStream fos = new FileOutputStream("templateFinal.xlsx")) {
			fos.write(data);
		}
		System.out.println("Terminado");
	}

}
