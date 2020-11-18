package rest.DB;


import rest.Model.Admin;
import rest.Model.Student;
import rest.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static UserDAO instance;
    private static List<User> users = new ArrayList<>();

    private UserDAO() {}

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }
    public User logIn(String email, String password) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email = ? and password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int id = resultSet.getInt(1);
            String curEmail = resultSet.getString(2);
            String curPassword = resultSet.getString(3);
            String firstName = resultSet.getString(4);
            String lastName = resultSet.getString(5);
            String role = resultSet.getString(6);
            int majorID = resultSet.getInt(7);
            int groupID = resultSet.getInt(8);
            int yearsOfStudy = resultSet.getInt(9);
            connection.close();
            if("admin".equals(role))
                return new Admin(id, curEmail, curPassword, firstName, lastName, role);
            else if("student".equals(role))
                return new Student(id, curEmail, curPassword, firstName, lastName, role, majorID, groupID, yearsOfStudy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> listAll() throws SQLException {

        Connection connection = DB.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            String curEmail = resultSet.getString(2);
            String curPassword = resultSet.getString(3);
            String firstName = resultSet.getString(4);
            String lastName = resultSet.getString(5);
            String role = resultSet.getString(6);
            int major_id = resultSet.getInt(7);
            int group_id = resultSet.getInt(8);
            int yearsOfStudy = resultSet.getInt(9);

            User user = new Student(id, curEmail, curPassword, firstName, lastName, role, major_id, group_id, yearsOfStudy);
            users.add(user);
        }
        connection.close();
        return users;
    }
}
