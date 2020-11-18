package rest.Services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rest.DB.UserDAO;
import rest.Model.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("auth")
public class Authentication {

    @Context
    HttpServletRequest request;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String convert)  {
        JsonObject json = new Gson().fromJson( convert, JsonObject.class);
        JsonObject data = new JsonObject();
        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();
        User loggedUser = UserDAO.getInstance().logIn(email, password);
        if(loggedUser == null) {
            data.addProperty("status", "error");
        } else {
            request.getSession().setAttribute("user", loggedUser);
            data.addProperty("status", "success");
        }
        return new Gson().toJson(data);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String logout() {
        request.getSession().invalidate();
        JsonObject data = new JsonObject();
        data.addProperty("status", "success");
        return new Gson().toJson(data);
    }
}
