package br.com.quicktipsenglish.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.quicktipsenglish.model.Tips;
import br.com.quicktipsenglish.persistence.TipsDAO;

import com.google.gson.Gson;

@Path("/tips")
public class TipsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTips() {
		final TipsDAO cateroyDAO = new TipsDAO();
		final List<Tips> tips = cateroyDAO.getTips();
		final Gson gson = new Gson();
		return Response.ok(gson.toJson(tips)).build();
	}

}
