package com.gnp.generator.domain.repository;

import java.io.IOException;

import com.gnp.generator.domain.model.LineaAzulTemplate;

public interface LineaAzulReportRepository {
	byte[] generate(LineaAzulTemplate template) throws IOException;
}
