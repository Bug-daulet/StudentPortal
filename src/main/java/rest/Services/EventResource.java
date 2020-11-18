package rest.Services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rest.DB.EventDAO;
import rest.Model.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Path("events")
public class EventResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEvent(@PathParam("id") int id) {
        return EventDAO.getInstance().listAllEvents().get(id - 1);
    }

    @GET
    @Path("listAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        return EventDAO.getInstance().listAllEvents();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addEvent(String convert) {
        JsonObject json = new Gson().fromJson( convert, JsonObject.class);
        String title = json.get("title").getAsString();
        String description = json.get("description").getAsString();
        int clubID = json.get("clubID").getAsInt();
        EventDAO.getInstance().addEvent(title, description, null, clubID);
        JsonObject data = new JsonObject();
        data.addProperty("status", "success");
        return new Gson().toJson(data);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateEvent(String convert) {
        JsonObject json = new Gson().fromJson( convert, JsonObject.class);
        int id = json.get("id").getAsInt();
        String title = json.get("title").getAsString();
        String description = json.get("description").getAsString();
        int clubID = json.get("clubID").getAsInt();
        Event event = new Event(id, title, description, null, clubID);
        EventDAO.getInstance().updateEvent(event);
        JsonObject data = new JsonObject();
        data.addProperty("status", "success");
        return new Gson().toJson(data);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteEvent(@PathParam("id") int id) throws SQLException {
        EventDAO.getInstance().deleteEvent(id);
        JsonObject json = new JsonObject();
        json.addProperty("status", "success");
        return new Gson().toJson(json);
    }
}
