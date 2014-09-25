package org.transparencia.gov2go.inicia;

import static org.transparencia.gov2go.model.constantes.Tipo.LIMPEZA_URBANA;

import java.io.IOException;
import java.io.InputStream;

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

/**
 * 
 * @author pedro-hos
 * Apenas para dar Uma Carga Inicial
 *
 */
@Startup
@Singleton
public class Inicia {
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Ocorrencias ocorrencias;
	
	@Inject
	protected Logger log;
	
	@PostConstruct
	public void popula () {
		
			log.info(" ###### Iniciando Processo de Popular Banco ###### ");
			
			log.info(" ###### Cadastro de Usuario ###### ");
			Usuario usuario = criaUsuario();
			usuarios.novo(usuario);
			
			log.info(" ###### Cadastro de Ocorrencia ###### ");
			Ocorrencia ocorrencia = geraOcorrencias();
			ocorrencias.novo(ocorrencia);
			
	}
	
	protected Ocorrencia geraOcorrencias() {
		
		Usuario usuario = usuarios.comEmail("pedro-hos@outlook.com");
		
		byte[] foto = null;
		
		try {
			foto = getImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Imagem imagem = Imagem.comFoto(foto)
							  .comExtensao(".jpg")
							  .comMimeType("image/jpg")
							  .comNome("ocorrencia_2_" + LIMPEZA_URBANA.toString())
							  .build();
		
		Localizacao localizacao = Localizacao.nerwork()
											 .comLatitude("123.012")
											 .comLongitude("-54.092")
											 .build();
		
		Ocorrencia ocorrencia = Ocorrencia.aberta()
										  .descricao("Problemas com pichação")
										  .titulo("Problemas no Galo Branco")
										  .doUsuario(usuario)
										  .comFoto(imagem)
										  .localizadoEm(localizacao)
										  .doTipo(LIMPEZA_URBANA)
										  .hoje()
										  .build();
		return ocorrencia;
	}

	private byte[] getImage() throws IOException {
		InputStream is = Inicia.class.getResourceAsStream("/pichado.jpg");
		byte[] foto = new byte[is.available()];
		
		is.read(foto);
		is.close();
		
		return foto;
	}
	
	protected Usuario criaUsuario () {
		
		Usuario usuario = Usuario.novo()
								 .comEmail("pedro-hos@outlook.com")
								 .comNome("Pedro Hos")
								 .build();
		return usuario;
		
	}
}
