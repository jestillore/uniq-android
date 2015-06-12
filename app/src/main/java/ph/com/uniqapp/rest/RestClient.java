package ph.com.uniqapp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by waelhe on 6/12/2015.
 */
public class RestClient {
    private static final String BASE_URL = "http://10.11.15.248";
    private static ApiService apiService;
    public RestClient() {
        Gson gson = new GsonBuilder().create();

        RequestInterceptor interceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", "");
            }
        };

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
