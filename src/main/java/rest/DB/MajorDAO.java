package rest.DB;

import rest.Model.Event;
import rest.Model.Major;

import java.sql.*;
import java.util.ArrayList;

public class MajorDAO {
    private static MajorDAO instance;
    private static ArrayList<Major> majors = new ArrayList<>();

    private MajorDAO() {}

    public static MajorDAO getInstance() {
        if(instance == null) {
            return new MajorDAO();
        }
        return instance;
    }


    public ArrayList<Major> listAllEvents() {
        if(majors.size() > 0 ) {
            return majors;
        }
        return getAllMajorDB();
    }

    public ArrayList<Major> getAllMajorDB() {
        try {
            Connection connection = DB.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from majors");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Major major = new Major(id, name);
                majors.add(major);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return majors;
    }
}
