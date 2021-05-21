package com.algaworks.algafoodapi.infrastructure.service.report;

public class ReportException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReportException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
