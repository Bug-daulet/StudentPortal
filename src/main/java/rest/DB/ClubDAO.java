package rest.DB;

import rest.Model.Club;
import rest.Model.Event;

import java.sql.*;
import java.util.ArrayList;

public class ClubDAO {
    private static ClubDAO instance;
    private static ArrayList<Club> clubs = new ArrayList<>();

    private ClubDAO() {}

    public static ClubDAO getInstance() {
        if(instance == null) {
            return new ClubDAO();
        }
        return instance;
    }


    public ArrayList<Club> listAllClubs() {
        if(clubs.size() > 0 ) {
            return clubs;
        }
        return getAllClubDB();
    }

    public ArrayList<Club> getAllClubDB() {
        try {
            Connection connection = DB.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from clubs");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Club club = new Club(id, name, description);
                clubs.add(club);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    public void addClub(String name, String description) {
        int id = addDBClub(name, description);
        Club club = new Club(id,name, description);
        listAllClubs().add(club);
    }

    private int addDBClub(String name, String description) {
        int id = 0;
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement("INSERT INTO clubs(title, description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.executeUpdate();

            ResultSet resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void updateClub(Club curClub) {
        listAllClubs();
        for (Club club : clubs) {
            if (club.getId() == curClub.getId()) {
                int index = clubs.indexOf(club);
                clubs.set(index, curClub);
                updateDBClub(curClub);
            }
        }
    }

    private void updateDBClub(Club club) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE clubs SET name=?, description=? WHERE id=?");
            pstmt.setString(1, club.getName());
            pstmt.setString(2, club.getDescription());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClub(int id) throws SQLException {
        listAllClubs();
        for (Club club : clubs) {
            if (club.getId() == id) {
                clubs.remove(club);
                deleteDBClub(id);
            }
        }
    }

    private void deleteDBClub(int id) throws SQLException {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM clubs WHERE id=?");
            pstmt.setInt(1, id);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
