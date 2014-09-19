package org.transparencia.gov2go.inicia;

import static org.transparencia.gov2go.model.constantes.Provedor.NETWORK;
import static org.transparencia.gov2go.model.constantes.TipoOcorrencia.LIMPEZA_URBANA;

import java.io.InputStream;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.transparencia.gov2go.model.impl.Imagem;
import org.transparencia.gov2go.model.impl.Localizacao;
import org.transparencia.gov2go.model.impl.Ocorrencia;
import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.impl.Ocorrencias;
import org.transparencia.gov2go.repository.impl.Usuarios;

/*
 * 	Classe para popular o Banco de Dados, e ter uma carga inicial
 */

@Startup
@Singleton
public class PopulaBanco {
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Ocorrencias ocorrencias;
	
	@Inject
	protected Logger log;
	
	@PostConstruct
	public void popula () {
		try {
			Usuario usuario = criaUsuario();
			usuarios.novo(usuario);
			
			Ocorrencia ocorrencia = geraOcorrencias();
			ocorrencias.novo(ocorrencia);
			
		} catch (Exception e) {
			log.info("Erro no Processo de preenchimento do Banco: " + e.getMessage());
			e.printStackTrace();
		} finally {
			log.info("Processo de preenchimento do Banco de Dados Completado");
		}
	}
	
	protected Ocorrencia geraOcorrencias() throws Exception {
		
		Usuario usuario = usuarios.todos().get(0);
		
		InputStream is = PopulaBanco.class.getResourceAsStream("/pichado.jpg");
		byte[] foto = new byte[is.available()];
		
		is.read(foto);
		is.close();
		
		Imagem imagem = new Imagem();
		imagem.setImagem(foto);
		imagem.setExtensao(".jpg");
		imagem.setMimeType("image/jpg");
		imagem.setNome("ocorrencia_2_" + LIMPEZA_URBANA.toString());
		
		Localizacao localizacao = new Localizacao();
		localizacao.setLatitude("123.012");
		localizacao.setLongitude("-54.092");
		localizacao.setProvedor(NETWORK);
		
		
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao("Problemas com pichação");
		ocorrencia.setTitulo("Problemas no Galo Branco");
		ocorrencia.setUsuario(usuario);
//		ocorrencia.setImagem(imagem);
		ocorrencia.setLocalizacao(localizacao);
		ocorrencia.setTipoOcorrencia(LIMPEZA_URBANA);
		ocorrencia.setDataCriacaoOcorrencia(LocalDate.now());
		
		return ocorrencia;
	}
	
	protected Usuario criaUsuario () {
		
		Usuario usuario = new Usuario();
		usuario.setNome("Pedro Hos");
		usuario.setEmail("pedrospsjc@gmail.com");
		
		return usuario;
		
	}
}
