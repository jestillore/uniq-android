package ph.com.uniqapp.rest;


import ph.com.uniqapp.model.AccessToken;

import java.util.ArrayList;

import ph.com.uniqapp.model.Category;
import ph.com.uniqapp.model.Comment;
import ph.com.uniqapp.model.Event;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by waelhe on 6/12/2015.
 */
public interface ApiService{

    @FormUrlEncoded
    @POST("/oauth/access_token")
    void login(@Field("username") String email, @Field("password") String password, @Field("client_id") String client_id, @Field("client_secret") String client_secret, @Field("grant_type") String grant_type, Callback<AccessToken> callback);

    @FormUrlEncoded
    @POST("/users")
    void register(@Field("username") String username,@Field("first_name") String first_name, @Field("last_name") String last_name, @Field("email") String email, @Field("password") String password, @Field("password_confirmation") String password_confirmation, Callback<String> callback);

    @GET("/category/follow/{id}")
    void follow(@Path("id") int id, Callback<Object> cb);

    @GET("/category/un-follow/{id}")
    void unfollow(@Path("id") int id, Callback<Object> cb);

    @GET("/categories")
    void getAllCategories(Callback<ArrayList<Category>> cb);

    @GET("/category/my-categories")
    void getMyCategories(Callback<ArrayList<Category>> cb);

    @GET("/events")
    void getEventFeeds(Callback<ArrayList<Event>> cb);

    @GET("/event/my-events")
    void getMyEvents(Callback<ArrayList<Event>> cb);

    @POST("/events")
    void postEvent(@Body Event event, Callback<Object> cb);

    @GET("/event/favorites")
    void getFavourites(Callback<ArrayList<Event>> cb);

    @GET("/event/mark-favorite/{id}")
    void markFavourite(@Path("id") int id, Callback<Object> cb);

    @GET("/event/unmark-favorite/{id}")
    void removeFavourite(@Path("id") int id, Callback<Object> cb);

    @FormUrlEncoded
    @POST("/comments")
    void postComment(@Field("event_id") int id, @Field("content") String content, Callback<Object> cb);

    @GET("/event/comments/{id}")
    void getComments(@Path("id") int id, Callback<ArrayList<Comment>> cb);
}
