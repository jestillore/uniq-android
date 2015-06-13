package ph.com.uniqapp;

import java.util.ArrayList;

import ph.com.uniqapp.model.AccessToken;
import ph.com.uniqapp.model.Category;
import ph.com.uniqapp.model.Event;
import ph.com.uniqapp.model.User;

/**
 * Created by waelhe on 6/13/2015.
 */
public class Uniq {

    private User user = null;
    private AccessToken accessToken = null;
    private ArrayList<Category> mine = new ArrayList<>();
    private Event current;

    private static Uniq uniq = null;

    private  Uniq() {

    }
    public static Uniq getInstance(){
            if(uniq == null)
                uniq = new Uniq();
        return uniq;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void setMine(ArrayList<Category> categories) {
        this.mine = categories;
    }

    public ArrayList<Category> getMine() {
        return mine;
    }

    public void setCurrentEvent(Event event) {
        this.current = event;
    }

    public Event getCurrentEvent() {
        return current;
    }
}
