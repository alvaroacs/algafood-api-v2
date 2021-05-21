package com.algaworks.algafoodapi.domain.service;

import java.util.List;

import com.algaworks.algafoodapi.domain.dto.VendaDiaria;
import com.algaworks.algafoodapi.domain.filter.VendaDiariaFilter;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
