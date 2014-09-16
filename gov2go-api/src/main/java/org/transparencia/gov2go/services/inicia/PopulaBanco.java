package org.transparencia.gov2go.services.inicia;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.transparencia.gov2go.constantes.Provedor;
import org.transparencia.gov2go.constantes.TipoOcorrencia;
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
	private Usuarios usuarioDao;
	
	@Inject
	private Ocorrencias ocorrenciaDao;
	
	@Inject
	protected Logger log;
	
	@PostConstruct
	public void popula () {
		try {
			Usuario usuario = criaUsuario();
			usuarioDao.novo(usuario);
			
			Ocorrencia ocorrencia = geraOcorrencias();
			ocorrenciaDao.novo(ocorrencia);
			
		} catch (Exception e) {
			log.info("Erro no Processo de preenchimento do Banco: " + e.getMessage());
			e.printStackTrace();
		} finally {
			log.info("Processo de preenchimento do Banco de Dados Completado");
		}
	}
	
	protected Ocorrencia geraOcorrencias() throws Exception {
		
		Usuario usuario = usuarioDao.todos().get(0);
		
		Imagem imagem = new Imagem();
		imagem.setImagem("Simulando uma imagem :D");
		
		Localizacao localizacao = new Localizacao();
		localizacao.setLatitude("123.012");
		localizacao.setLongitude("-54.092");
		localizacao.setProvedor(Provedor.NETWORK);
		
		
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao("Problemas com pichação");
		ocorrencia.setTitulo("Problemas no Galo Branco");
		ocorrencia.setUsuario(usuario);
		ocorrencia.setImagem(imagem);
		ocorrencia.setLocalizacao(localizacao);
		ocorrencia.setTipoOcorrencia(TipoOcorrencia.LIMPEZA_URBANA);
		ocorrencia.setDataCriacaoOcorrencia(new Date());
		
		return ocorrencia;
	}
	
	protected Usuario criaUsuario () {
		
		Usuario usuario = new Usuario();
		usuario.setNome("Pedro Hos");
		usuario.setEmail("pedrospsjc@gmail.com");
		
		return usuario;
		
	}
}
