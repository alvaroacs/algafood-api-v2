package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.input.ItemPedidoInput;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();

		var cidadeToEnderecoModelTypeMap = mapper.createTypeMap(Endereco.class, EnderecoModel.class);
		cidadeToEnderecoModelTypeMap.<String>addMapping(src -> src.getCidade().getNome(),
				(dest, value) -> dest.setCidade(value));
		cidadeToEnderecoModelTypeMap.<String>addMapping(src -> src.getCidade().getEstado().getNome(),
				(dest, value) -> dest.setEstado(value));
		
		mapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class).addMappings(m -> m.skip(ItemPedido::setId));

//		mapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//		.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

		return mapper;
	}
}
