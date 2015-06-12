package ph.com.uniqapp.rest;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by waelhe on 6/12/2015.
 */
public interface ApiService{

    @FormUrlEncoded
    @POST("/login")
    void login(@Field("username") String email, @Field("password") String password, Callback<String> callback);

}
