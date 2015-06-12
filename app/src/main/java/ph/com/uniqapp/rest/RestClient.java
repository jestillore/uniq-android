package ph.com.uniqapp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by waelhe on 6/12/2015.
 */
public class RestClient {
    private static final String BASE_URL = "http://ec2-52-1-43-237.compute-1.amazonaws.com";
    private ApiService apiService;
    public RestClient() {
        Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(ApiService.class);

    }

    public ApiService getApiService(){
        return apiService;
    }

}
