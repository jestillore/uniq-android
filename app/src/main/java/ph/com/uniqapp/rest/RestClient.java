package ph.com.uniqapp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ph.com.uniqapp.Uniq;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by waelhe on 6/12/2015.
 */
public class RestClient {
    private static final String BASE_URL = "http://192.168.1.100";
    private static ApiService apiService;
    public RestClient() {
        Gson gson = new GsonBuilder().create();

        RequestInterceptor interceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                if(Uniq.getInstance().getAccessToken() != null) {
                    request.addHeader("Authorization", Uniq.getInstance().getAccessToken().getToken_type() + " " +Uniq.getInstance().getAccessToken().getAccess_token());
                }
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(interceptor)
                .build();

        apiService = restAdapter.create(ApiService.class);

    }

    public ApiService getApiService(){
        return apiService;
    }

}
