package rest.DB;

import rest.Model.Group;
import rest.Model.Major;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupDAO {
    private static GroupDAO instance;
    private static ArrayList<Group> groups = new ArrayList<>();

    private GroupDAO() {}

    public static GroupDAO getInstance() {
        if(instance == null) {
            return new GroupDAO();
        }
        return instance;
    }


    public ArrayList<Group> listAllGroups() {
        if(groups.size() > 0 ) {
            return groups;
        }
        return getAllGroupDB();
    }

    public ArrayList<Group> getAllGroupDB() {
        try {
            Connection connection = DB.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from majors");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Group group = new Group(id, name);
                groups.add(group);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
}
