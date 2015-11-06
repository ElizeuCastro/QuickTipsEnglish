package br.com.quicktipsenglish.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.quicktipsenglish.model.Category;
import br.com.quicktipsenglish.persistence.CateroyDAO;

import com.google.gson.Gson;

@Path("/tips")
public class TipsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTips() {
		final CateroyDAO cateroyDAO = new CateroyDAO();
		final List<Category> tips = cateroyDAO.categories();
		final Gson gson = new Gson();
		return Response.ok(gson.toJson(tips)).build();
	}

}
