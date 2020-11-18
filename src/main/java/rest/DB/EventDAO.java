package rest.DB;

import rest.Model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class EventDAO {
    private static EventDAO instance;
    private static ArrayList<Event> events = new ArrayList<>();

    private EventDAO() {}

    public static EventDAO getInstance() {
        if(instance == null) {
            return new EventDAO();
        }
        return instance;
    }


    public ArrayList<Event> listAllEvents() {
        if(events.size() > 0 ) {
            return events;
        }
        return getAllEventDB();
    }

    public ArrayList<Event> getAllEventDB() {
        try {
            Connection connection = DB.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from events");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                Timestamp date = resultSet.getTimestamp(4);
                int clubID = resultSet.getInt(5);
                Event event = new Event(id, title, description, date, clubID);
                events.add(event);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void addEvent(String title, String description, Date date, int clubID) {
        int id = addDBEvent(title, description, date, clubID);
        Event event = new Event(id,title, description, date, clubID);
        listAllEvents().add(event);
    }

    private int addDBEvent(String title, String description, Date date, int clubID) {
        int id = 0;
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement("INSERT INTO events(title, description, event_date, club_id) VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setDate(3, (java.sql.Date) date);
            pstmt.setInt(4, clubID);
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

    public void updateEvent(Event curEvent) {
        listAllEvents();
        for (Event event : events) {
            if (event.getId() == curEvent.getId()) {
                int index = events.indexOf(event);
                events.set(index, curEvent);
                updateDBEvent(curEvent);
            }
        }
    }

    private void updateDBEvent(Event event) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE events SET title=?, description=?, club_id=?  WHERE id=?");
            pstmt.setString(1, event.getTitle());
            pstmt.setString(2, event.getDescription());
            pstmt.setInt(3, event.getClubID());
            pstmt.setInt(4, event.getId());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(int id) throws SQLException {
        listAllEvents();
        for (Event event : events) {
            if (event.getId() == id) {
                events.remove(event);
                deleteDBEvent(id);
            }
        }
    }

    private void deleteDBEvent(int id) throws SQLException {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM events WHERE id=?");
            pstmt.setInt(1, id);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
