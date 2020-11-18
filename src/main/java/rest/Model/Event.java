package rest.Model;

import java.util.Date;

public class Event extends Publishing {
    public Event(int id, String title, String description, Date date, int clubID) {
        super(id, title, description, date, clubID);
    }
}
