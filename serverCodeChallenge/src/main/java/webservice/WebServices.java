package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("prueba")
public class WebServices {
	
	@GET
	@Path("ws")
	@Produces("application/json")
	public boolean dataPrueba() {
		return true;
	}

}
