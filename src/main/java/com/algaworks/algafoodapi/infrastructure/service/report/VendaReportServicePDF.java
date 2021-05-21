package com.algaworks.algafoodapi.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.filter.VendaDiariaFilter;
import com.algaworks.algafoodapi.domain.service.VendaQueryService;
import com.algaworks.algafoodapi.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class VendaReportServicePDF implements VendaReportService {

	@Autowired
	private VendaQueryService vendaQueryService;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		try {
			var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");
			var parametros = new HashMap<String, Object>();
			var dataSource = new JRBeanCollectionDataSource(
					vendaQueryService.consultarVendasDiarias(filtro, timeOffset));

			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ReportException("Não foi possível emitir o relatório de vendas diárias", e);
		}
	}
}
