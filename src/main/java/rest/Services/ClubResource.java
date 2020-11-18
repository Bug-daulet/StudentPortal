package rest.Services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rest.DB.ClubDAO;
import rest.DB.ClubDAO;
import rest.Model.Club;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("club")
public class ClubResource {
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Club getClub(@PathParam("id") int id) {
        return ClubDAO.getInstance().listAllClubs().get(id - 1);
    }

    @GET
    @Path("listAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Club> getAllClubs() {
        return ClubDAO.getInstance().listAllClubs();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addClub(String convert) {
        JsonObject json = new Gson().fromJson( convert, JsonObject.class);
        String name = json.get("name").getAsString();
        String description = json.get("description").getAsString();
        ClubDAO.getInstance().addClub(name, description);
        JsonObject data = new JsonObject();
        data.addProperty("status", "success");
        return new Gson().toJson(data);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateClub(String convert) {
        JsonObject json = new Gson().fromJson( convert, JsonObject.class);
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        String description = json.get("description").getAsString();
        Club club = new Club(id, name, description);
        ClubDAO.getInstance().updateClub(club);
        JsonObject data = new JsonObject();
        data.addProperty("status", "success");
        return new Gson().toJson(data);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteClub(@PathParam("id") int id) throws SQLException {
        ClubDAO.getInstance().deleteClub(id);
        JsonObject json = new JsonObject();
        json.addProperty("status", "success");
        return new Gson().toJson(json);
    }
}
