package rest.Services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rest.DB.NewsDAO;
import rest.Model.News;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("news")
public class NewsResource {
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News getNews(@PathParam("id") int id) {
        return NewsDAO.getInstance().listAllNews().get(id - 1);
    }

    @GET
    @Path("listAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNews() {
        return NewsDAO.getInstance().listAllNews();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addNews(String convert) {
        JsonObject json = new Gson().fromJson( convert, JsonObject.class);
        String title = json.get("title").getAsString();
        String description = json.get("description").getAsString();
        int clubID = json.get("clubID").getAsInt();
        NewsDAO.getInstance().addNews(title, description, null, clubID);
        JsonObject data = new JsonObject();
        data.addProperty("status", "success");
        return new Gson().toJson(data);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateNews(String convert) {
        JsonObject json = new Gson().fromJson( convert, JsonObject.class);
        int id = json.get("id").getAsInt();
        String title = json.get("title").getAsString();
        String description = json.get("description").getAsString();
        int clubID = json.get("clubID").getAsInt();
        News news = new News(id, title, description, null, clubID);
        NewsDAO.getInstance().updateNews(news);
        JsonObject data = new JsonObject();
        data.addProperty("status", "success");
        return new Gson().toJson(data);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteNews(@PathParam("id") int id) throws SQLException {
        NewsDAO.getInstance().deleteNews(id);
        JsonObject json = new JsonObject();
        json.addProperty("status", "success");
        return new Gson().toJson(json);
    }
}
