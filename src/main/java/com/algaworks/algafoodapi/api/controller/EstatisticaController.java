package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.domain.dto.VendaDiaria;
import com.algaworks.algafoodapi.domain.filter.VendaDiariaFilter;
import com.algaworks.algafoodapi.domain.service.VendaQueryService;
import com.algaworks.algafoodapi.domain.service.VendaReportService;

@RestController
@RequestMapping(path = "/estatisticas", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstatisticaController {

	@Autowired
	private VendaQueryService vendaService;

	@Autowired
	private VendaReportService vendaReportService;

	@GetMapping("/vendas-diarias")
	public ResponseEntity<List<VendaDiaria>> consultarVendasDiarias(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
		var vendas = vendaService.consultarVendasDiarias(filtro, timeOffset);
		return ResponseEntity.ok(vendas);
	}

	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> relatorioVendasDiarias(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
		var pdf = vendaReportService.emitirVendasDiarias(filtro, timeOffset);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf")
				.contentType(MediaType.APPLICATION_PDF)
				.contentLength(pdf.length)
				.body(pdf);
	}
}
