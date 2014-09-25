package org.transparencia.gov2go.endpoints;

import static org.jboss.shrinkwrap.api.asset.EmptyAsset.INSTANCE;
import static org.junit.Assert.*;
import static org.transparencia.gov2go.constantes.ConstantesTest.*;

import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.transparencia.gov2go.endpoints.impl.UsuarioEndpointImpl;
import org.transparencia.gov2go.endpoints.util.JaxRSActivator;
import org.transparencia.gov2go.model.impl.Usuario;
import org.transparencia.gov2go.repository.impl.Usuarios;
import org.transparencia.gov2go.services.impl.UsuarioService;

@RunWith(Arquillian.class)
@RunAsClient
public class UsuarioEndpointTest {

	private static final String USUARIO_CONTEXT = "rest/v1/usuario";

	@Deployment(testable = true)
	public static Archive<?> createTestArchive() {
		
		return ShrinkWrap.create(WebArchive.class, WAR_NAME)
				.addPackages( false, MODEL, MODEL_CONSTANTES, MODEL_IMPL, 
									 MODEL_BUILDER, UTIL, REPOSITORY, SERVICE )
				.addClasses( Usuarios.class, 
							 UsuarioEndpoint.class, 
							 UsuarioService.class, 
							 UsuarioEndpointImpl.class, 
							 JaxRSActivator.class )
				.addAsResource( TEST_PERSISTENCE, PERSISTENCE )
				.addAsWebInfResource( INSTANCE, BEANS )
				.addAsWebInfResource( TEST_DS );
	}
	
	@ArquillianResource
	private URL deploymentURL;
	
	@Inject
	Logger log;

	@Test
	public void deveCadastrarCorretamente (@ArquillianResteasyResource(USUARIO_CONTEXT) ResteasyWebTarget webTarget) throws Exception {
		
		Usuario build = Usuario.novo()
				.comNome("Taís")
				.comEmail("tais@monique.com")
				.build();
		
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).buildPost(Entity.json(build)).invoke();
		
		assertEquals( 201, response.getStatus() );
		
		//Usuario entity = (Usuario) response.getEntity();
		//assertEquals(deploymentURL.toString().concat(USUARIO_CONTEXT).concat("/").concat(String.valueOf(entity.getId())), response.getHeaderString(LOCATION));
			
	}
}
