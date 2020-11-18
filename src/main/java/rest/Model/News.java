package rest.Model;

import java.util.Date;

public class News extends Publishing {
    public News(int id, String title, String description, Date date, int clubID) {
        super(id, title, description, date, clubID);
    }
}
