package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PermissaoController;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Permissao;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public PermissaoModelAssembler() {
		super(PermissaoController.class, PermissaoModel.class);
	}
	
	@Override
	public PermissaoModel toModel(Permissao permissao) {
		var permissaoModel = createModelWithId(permissao.getId(), permissao);
		mapper.map(permissao, permissaoModel);
		
		if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
			permissaoModel.add(algaLinks.linkToPermissoes());			
		}
		
		return permissaoModel;
	}
}
