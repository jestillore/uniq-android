package ph.com.uniqapp;

import ph.com.uniqapp.model.AccessToken;
import ph.com.uniqapp.model.User;

/**
 * Created by waelhe on 6/13/2015.
 */
public class Uniq {

    private User user = null;
    private AccessToken accessToken = null;

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

}
