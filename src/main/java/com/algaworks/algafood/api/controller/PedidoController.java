package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.disassembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.api.util.ApiUtils;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.PedidoService;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<Page<PedidoResumoModel>> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
		var pedidosPage = pedidoService.pesquisar(pedidoFilter, pageable);
		var pedidosModel = pedidoModelAssembler.toCollectionModel(pedidosPage.getContent());
		var pedidosModelPage = new PageImpl<PedidoResumoModel>(pedidosModel, pageable, pedidosPage.getTotalElements());
		return ResponseEntity.ok(pedidosModelPage);
	}
	
	@GetMapping("/{pedidoCodigo}")
	public ResponseEntity<PedidoModel> buscar(@PathVariable String pedidoCodigo) {
		var pedido = pedidoService.buscar(pedidoCodigo);
		var pedidoModel = pedidoModelAssembler.toModel(pedido);
		return ResponseEntity.ok(pedidoModel);
	}
	
	@PostMapping
	public ResponseEntity<PedidoModel> emitir(@RequestBody @Valid PedidoInput pedidoInput) {
		var pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
		
		var cliente = new Usuario();
		cliente.setId(1L);
		
		pedido.setCliente(cliente);
		pedido = pedidoService.emitir(pedido);
		
		var pedidoModel = pedidoModelAssembler.toModel(pedido);
		return ResponseEntity.created(ApiUtils.uri(pedidoModel.getCodigo())).body(pedidoModel);
	}
	
}
