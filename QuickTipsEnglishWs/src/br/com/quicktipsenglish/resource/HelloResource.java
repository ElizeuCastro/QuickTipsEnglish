package br.com.quicktipsenglish.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.quicktipsenglish.model.Category;
import br.com.quicktipsenglish.persistence.CateroyDAO;

@Path("/todo")
public class HelloResource {

	@GET
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String teste() {
		
		CateroyDAO cateroyDAO = new CateroyDAO();
		cateroyDAO.save(new Category(0, "maria", null));
		
		System.out.println(cateroyDAO.categories());
		
		return "teste";
	}
}