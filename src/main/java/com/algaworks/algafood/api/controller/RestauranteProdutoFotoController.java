package com.algaworks.algafood.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.api.openapi.controller.RestauranteProdutoFotoControllerOpenAPI;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenAPI {

	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;

	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;

	@Override
	@GetMapping
	public ResponseEntity<FotoProdutoModel> buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		var fotoProduto = catalogoFotoProdutoService.buscar(restauranteId, produtoId);
		var fotoProdutoModel = fotoProdutoModelAssembler.toModel(fotoProduto);
		return ResponseEntity.ok(fotoProdutoModel);
	}

	@Override
	@GetMapping(produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> servir(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestHeader(name = "Accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			var fotoRecuperada = catalogoFotoProdutoService.servir(restauranteId, produtoId);

			var fotoRecuperadaMediaType = MediaType.parseMediaType(fotoRecuperada.getContentType());
			var mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			verificarCompatibilidadeMediaType(fotoRecuperadaMediaType, mediaTypesAceitas);

			if (fotoRecuperada.temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			} else {
				return ResponseEntity.ok().contentType(fotoRecuperadaMediaType)
						.contentLength(fotoRecuperada.getTamanho())
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FotoProdutoModel> atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

		var arquivo = fotoProdutoInput.getArquivo();

		var foto = new FotoProduto();
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		foto.setTamanho(arquivo.getSize());

		foto = catalogoFotoProdutoService.salvar(restauranteId, produtoId, foto, arquivo.getInputStream());

		var fotoProdutoModel = fotoProdutoModelAssembler.toModel(foto);

		return ResponseEntity.ok(fotoProdutoModel);
	}

	@Override
	@DeleteMapping
	public ResponseEntity<Void> remover(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		catalogoFotoProdutoService.remover(restauranteId, produtoId);
		return ResponseEntity.noContent().build();
	}

	private void verificarCompatibilidadeMediaType(MediaType contentType, List<MediaType> mediaTypesAceitas)
			throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(contentType));

		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
}
