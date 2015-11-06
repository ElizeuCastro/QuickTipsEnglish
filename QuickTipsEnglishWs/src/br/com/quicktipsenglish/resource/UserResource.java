package br.com.quicktipsenglish.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.quicktipsenglish.model.User;
import br.com.quicktipsenglish.persistence.UserDAO;

import com.google.gson.Gson;

@Path("/user")
public class UserResource {

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@FormParam("email") String email,
			@FormParam("nick_name") String nickName,
			@FormParam("password") String password) {
		final UserDAO userDAO = new UserDAO();
		User user = userDAO.save(new User(email, nickName, password));
		Response response = Response.status(Status.NOT_FOUND).build();
		if (user != null) {
			final Gson gson = new Gson();
			if (user.isSaved()) {
				response = Response.ok(gson.toJson(user)).build();
			}
		}
		return response;
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("nick_name") String nickName,
			@FormParam("password") String password) {
		final UserDAO userDAO = new UserDAO();
		final boolean sucess = userDAO.login(new User(nickName, password));
		Response response = Response.status(Status.NOT_FOUND).build();
		if (sucess) {
			response = Response.ok().build();
		}
		return response;
	}

}
