package rest.DB;


import rest.Model.News;

import java.sql.*;
import java.util.ArrayList;

public class NewsDAO {
    private static NewsDAO instance;
    private static ArrayList<News> newS = new ArrayList<>();

    private NewsDAO() {}

    public static NewsDAO getInstance() {
        if(instance == null) {
            return new NewsDAO();
        }
        return instance;
    }


    public ArrayList<News> listAllNews() {
        if(newS.size() > 0 ) {
            return newS;
        }
        return getAllNewsDB();
    }

    public ArrayList<News> getAllNewsDB() {
        try {
            Connection connection = DB.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from news");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                Timestamp date = resultSet.getTimestamp(4);
                int clubID = resultSet.getInt(5);
                News news = new News(id, title, description, date, clubID);
                newS.add(news);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newS;
    }

    public void addNews(String title, String description, java.util.Date date, int clubID) {
        int id = addDBNews(title, description, date, clubID);
        News news = new News(id,title, description, date, clubID);
        listAllNews().add(news);
    }

    private int addDBNews(String title, String description, java.util.Date date, int clubID) {
        int id = 0;
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement("INSERT INTO news(title, description, event_date, club_id) VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
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

    public void updateNews(News curNews) {
        listAllNews();
        for (News news : newS) {
            if (news.getId() == curNews.getId()) {
                int index = newS.indexOf(news);
                newS.set(index, curNews);
                updateDBNews(curNews);
            }
        }
    }

    private void updateDBNews(News news) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE USERS SET title=?, description=?, club_id=?  WHERE id=?");
            pstmt.setString(1, news.getTitle());
            pstmt.setString(2, news.getDescription());
            pstmt.setInt(3, news.getClubID());
            pstmt.setInt(4, news.getId());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNews(int id) throws SQLException {
        listAllNews();
        for (News news : newS) {
            if (news.getId() == id) {
                newS.remove(news);
                deleteDBEvent(id);
            }
        }
    }

    private void deleteDBEvent(int id) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM news WHERE id=?");
            pstmt.setInt(1, id);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
