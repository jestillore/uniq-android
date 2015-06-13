package ph.com.uniqapp.rest;

import ph.com.uniqapp.model.AccessToken;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

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
}
