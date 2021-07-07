package com.gnp.generator.infraestructura.repository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gnp.generator.domain.model.LineaAzulTemplate;
import com.gnp.generator.domain.model.LineaAzulTemplate.Plan;
import com.gnp.generator.domain.repository.LineaAzulReportRepository;

public class LineaAzulReportExcelRepository implements LineaAzulReportRepository {

	private String fileLocation = LineaAzulReportExcelRepository.class.getResource("/template.xlsx").getFile();
	private static final int HEADER_CLIENT_NAME_INDEX = 6;
	private static final int HEADER_TYPE_INDEX = 7;
	private static final int HEADER_BUSINESS_INDEX = 9;
	private static final int HEADER_OPTION_INDEX = 10;
	private static final int HEADER_EFFECTIVE_DATE_START_INDEX = 12;
	private static final int HEADER_EFFECTIVE_DATE_END_INDEX = 13;

	@Override
	public byte[] generate(LineaAzulTemplate template) throws IOException {
		FileInputStream file = new FileInputStream(new File(fileLocation));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Workbook workbook = new XSSFWorkbook(file);
		try {
			Sheet sheet = workbook.getSheetAt(0);

			sheet.getRow(HEADER_CLIENT_NAME_INDEX).getCell(0).setCellValue(template.getHeader().getClientName());
			sheet.getRow(HEADER_TYPE_INDEX).getCell(0).setCellValue(template.getHeader().getType());
			sheet.getRow(HEADER_BUSINESS_INDEX).getCell(1).setCellValue(template.getHeader().getBusiness());
			sheet.getRow(HEADER_OPTION_INDEX).getCell(1).setCellValue(template.getHeader().getOption());
			sheet.getRow(HEADER_EFFECTIVE_DATE_START_INDEX).getCell(1)
					.setCellValue(template.getHeader().getEffectiveDate().getStart());
			sheet.getRow(HEADER_EFFECTIVE_DATE_END_INDEX).getCell(1)
					.setCellValue(template.getHeader().getEffectiveDate().getEnd());

			for (int x = 0, celdaCombinada = 2; x <= template.getPlanes().size() - 1; x++, celdaCombinada += 2) {
				Plan plan = template.getPlanes().get(x);

				CellStyle cellStyleNombrePlan = copyCellStyle(sheet, 17, 2);
				CellStyle cellStyleCirculoMedico = copyCellStyle(sheet, 18, 2);
				CellStyle cellStyleCirculoMedicoReembolso = copyCellStyle(sheet, 19, 2);
				CellStyle cellStyleCoberturaTerritorioNacional = copyCellStyle(sheet, 20, 2);
				CellStyle cellStyleCoberturaTerritorioExtranjero = copyCellStyle(sheet, 21, 2);
				CellStyle cellStyle1SumaAseguradaNacional = copyCellStyle(sheet, 22, 2);
				CellStyle cellStyleBlank1= copyCellStyle(sheet, 23, 2);
				CellStyle cellStyleDeducibleNacional = copyCellStyle(sheet, 24, 2);
				CellStyle cellStyleBlank2 = copyCellStyle(sheet, 25, 2);
				CellStyle cellStyleCoaseguroNacional = copyCellStyle(sheet, 26, 2);
				CellStyle cellStyleBlank3 = copyCellStyle(sheet, 27, 2);
				CellStyle cellStyleTopeCoaseguro = copyCellStyle(sheet, 28, 2);
				
								
				setText(sheet, 17, celdaCombinada, cellStyleNombrePlan, plan.getNombre());
				setText(sheet, 18, celdaCombinada, cellStyleCirculoMedico, plan.getCirculoMedico());
				setText(sheet, 19, celdaCombinada, cellStyleCirculoMedicoReembolso, plan.getCirculoMedicoReembolso());
				setText(sheet, 20, celdaCombinada, cellStyleCoberturaTerritorioNacional, plan.getCoberturaTerritorioNacional());
				setText(sheet, 21, celdaCombinada, cellStyleCoberturaTerritorioExtranjero, plan.getCoberturaTerritorioExtranjero());
				

				/*sheet.getRow(17).getCell(celdaCombinada).setCellValue(plan.getNombre());
				sheet.getRow(18).getCell(celdaCombinada).setCellValue(plan.getCirculoMedico());
				sheet.getRow(19).getCell(celdaCombinada).setCellValue(plan.getCirculoMedicoReembolso());
				sheet.getRow(20).getCell(celdaCombinada).setCellValue(plan.getCoberturaTerritorioNacional());
				sheet.getRow(21).getCell(celdaCombinada).setCellValue(plan.getCoberturaTerritorioExtranjero());
				sheet.getRow(22).getCell(celdaCombinada).setCellValue(plan.getSumaAseguradaNacional().getSuma());
				sheet.getRow(22).getCell(celdaCombinada + 1).setCellValue(plan.getSumaAseguradaNacional().getUnidad());

				sheet.getRow(24).getCell(celdaCombinada).setCellValue(plan.getDeducibleNacional().getSuma());
				sheet.getRow(24).getCell(celdaCombinada + 1).setCellValue(plan.getDeducibleNacional().getUnidad());

				sheet.getRow(26).getCell(celdaCombinada).setCellValue(plan.getCoaseguroNacional());

				sheet.getRow(28).getCell(celdaCombinada).setCellValue(plan.getTopeCoaseguro().getSuma());
				sheet.getRow(28).getCell(celdaCombinada + 1).setCellValue(plan.getTopeCoaseguro().getUnidad());*/
			}

			workbook.write(outputStream);
		} catch (Exception e) {
			throw e;
		} finally {
			workbook.close();
		}
		return outputStream.toByteArray();
	}

	private CellStyle copyCellStyle(Sheet sheet, int row, int column) {
		CellStyle style = sheet.getRow(row).getCell(column).getCellStyle();
		return style;
	}
	
	private void setText(Sheet sheet, int row, int column, CellStyle cellStyle, String value) {
		sheet.getRow(row).getCell(column).setCellStyle(cellStyle);

		sheet.getRow(row).getCell(column).setCellValue(value);
	}
}
