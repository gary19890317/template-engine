package com.gnp.generator.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LineaAzulTemplate {
	private Header header;
	private List<Plan> planes;

	@Data
	@AllArgsConstructor
	public static class Header {
		private String clientName;
		private String type;
		private String business;
		private String option;
		private EffectiveDate effectiveDate;
	}

	@Data
	@AllArgsConstructor
	public static class Plan {
		private String nombre;
		private String circuloMedico;
		private String circuloMedicoReembolso;
		private String coberturaTerritorioNacional;
		private String coberturaTerritorioExtranjero;
		private Total sumaAseguradaNacional;
		private Total deducibleNacional;
		private String coaseguroNacional;
		private Total topeCoaseguro;
	}

	@Data
	@AllArgsConstructor
	public static class Total {
		private int suma;
		private String unidad;
	}

	@Data
	@AllArgsConstructor
	public static class EffectiveDate {
		private String start;
		private String end;
	}
}
