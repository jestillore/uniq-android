package ph.com.uniqapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by waelhe on 6/12/2015.
 */
public class Event implements Serializable {
    private int id;
    private String title;
    private String description;
    private String slug;
    private String start_datetime;
    private String end_datetime;
    private String venue;
    public int category_id;
    private ArrayList<Category> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStart_date() {
        return start_datetime;
    }

    public void setStart_date(String start_date) {
        this.start_datetime = start_date;
    }

    public String getEnd_date() {
        return end_datetime;
    }

    public void setEnd_date(String end_date) {
        this.end_datetime = end_date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setCategoryId(int id) {
        this.category_id = id;
    }

    public Category getCategory() {
        if (categories.size() == 0)
            return null;
        return categories.get(0);
    }
}
