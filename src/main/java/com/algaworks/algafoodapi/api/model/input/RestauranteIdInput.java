package com.algaworks.algafoodapi.api.model.input;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteIdInput {

	@NotNull
	private Long id;
}
