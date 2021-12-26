package example.jersey;

import example.module1.Module1;
import example.module2.Module2;
import example.service.MyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.glassfish.jersey.process.internal.RequestScoped;


@RequestScoped
@Path("myresource")
public class MyResource {

	@Inject
	Module1 module1;
	@Inject
	Module2 module2;

	MyService service;

	@Inject
	public MyResource(MyService service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt(@Context UriInfo uriInfo) {
	    module1.do1();
	    module2.do2();
		return service.get();
	}
}