package org.transparencia.gov2go.model.builder;

import org.transparencia.gov2go.model.impl.Imagem;
import org.transparencia.gov2go.model.impl.Ocorrencia;

public class ImagemBuilder {

	private Imagem imagem;
	
	public ImagemBuilder() {
		novaImagem();
	}

	public ImagemBuilder(byte[] foto) {
		novaImagem();
		imagem.setImagem(foto);
	}
	
	public ImagemBuilder comNome ( String nome) {
		imagem.setNome(nome);
		return this;
	}
	
	public ImagemBuilder comMimeType ( String mimeType) {
		imagem.setMimeType(mimeType);
		return this;
	}
	
	public ImagemBuilder comExtensao ( String extensao ) {
		imagem.setExtensao(extensao);
		return this;
	}
	
	public ImagemBuilder paraOcorrencia ( Ocorrencia ocorrencia ) {
		return this;
	}
	
	public ImagemBuilder comFoto ( byte[] foto ) {
		imagem.setImagem(foto);
		return this;
	}
	
	public Imagem build() {
		return imagem;
	}
	
	private void novaImagem() {
		imagem = new Imagem();
	}

}
