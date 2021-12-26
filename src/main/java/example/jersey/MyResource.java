package example.jersey;

import javax.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import example.guice.Service;
import org.glassfish.jersey.process.internal.RequestScoped;

@RequestScoped
@Path("myresource")
public class MyResource {
	@Inject
	private Service service;

	public MyResource() {

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		System.out.println(service);
		return service.get();
	}
}