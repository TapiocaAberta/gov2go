package org.transparencia.gov2go.endpoints;

import static org.jboss.shrinkwrap.api.asset.EmptyAsset.INSTANCE;
import static org.transparencia.gov2go.constantes.ConstantesTest.BEANS;
import static org.transparencia.gov2go.constantes.ConstantesTest.MODEL;
import static org.transparencia.gov2go.constantes.ConstantesTest.MODEL_BUILDER;
import static org.transparencia.gov2go.constantes.ConstantesTest.MODEL_CONSTANTES;
import static org.transparencia.gov2go.constantes.ConstantesTest.MODEL_IMPL;
import static org.transparencia.gov2go.constantes.ConstantesTest.PERSISTENCE;
import static org.transparencia.gov2go.constantes.ConstantesTest.REPOSITORY;
import static org.transparencia.gov2go.constantes.ConstantesTest.TEST_DS;
import static org.transparencia.gov2go.constantes.ConstantesTest.TEST_PERSISTENCE;
import static org.transparencia.gov2go.constantes.ConstantesTest.UTIL;
import static org.transparencia.gov2go.constantes.ConstantesTest.WAR_NAME;

import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.transparencia.gov2go.endpoints.impl.UsuarioEndpointImpl;
import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.impl.Usuarios;

@RunWith(Arquillian.class)
public class UsuarioEndpointTest {

	@Deployment(testable = false)
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, WAR_NAME)
				.addPackage(MODEL)
				.addPackage(MODEL_CONSTANTES)
				.addPackage(MODEL_IMPL)
				.addPackage(MODEL_BUILDER)
				.addPackage(UTIL)
				.addPackage(REPOSITORY)
				.addClasses( Usuarios.class, UsuarioEndpoint.class, UsuarioEndpointImpl.class)
				.addAsResource(TEST_PERSISTENCE, PERSISTENCE)
				.addAsWebInfResource(INSTANCE, BEANS)
				.addAsWebInfResource(TEST_DS);
	}
	
	@Inject
	Logger log;
	
	@ArquillianResource
	private URL deploymentURL;

	@Test
	public void teste (@ArquillianResteasyResource("api/v1/usuario") WebTarget webTarget) throws Exception {
		
		// Given
		
		final Invocation.Builder invocationBuilder = webTarget.request();

		Usuario build = Usuario.novo()
				.comNome("Taís")
				.comEmail("tais@monique.com")
				.build();
		// When
		Entity<Usuario> json = Entity.json(build);
		
		final Response response = invocationBuilder.buildPost(json).invoke();
		
		// Then
		Assert.assertEquals(200, response.getStatus() );
			
	}
}
