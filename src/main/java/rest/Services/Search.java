//package rest.Services;
//
//import com.google.gson.JsonObject;
//import rest.DB.UserDAO;
//import rest.Model.User;
//
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.sql.SQLException;
//import java.util.List;
//
//@Path("Search")
//public class Search {
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public User searchUser(@FormParam("fname") String fname, @FormParam("lname") String lname) throws SQLException {
//        List<User> users = UserDAO.getInstance().listAll();
//        JsonObject data = new JsonObject();
//        for(User user: users) {
//            if(user.getFirstName().equals(fname) && user.getLastName().equals(lname)) {
//                return user;
//            }
//        }
//    }
//}
