package ph.com.uniqapp.authentication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ph.com.uniqapp.MainActivity;
import ph.com.uniqapp.R;
import ph.com.uniqapp.Uniq;
import ph.com.uniqapp.fragments.HomeFragment;
import ph.com.uniqapp.model.AccessToken;
import ph.com.uniqapp.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by waelhe on 6/12/2015.
 */
public class LoginActivity extends FragmentActivity{
    Button login;
    TextView username;
    TextView password;
    TextView tv_register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        tv_register = (TextView) findViewById(R.id.tv_register);
        username.setText("ejillberth@gmail.com");
        password.setText("imc3bu");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin(username.getText().toString(), password.getText().toString());
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void doLogin(String username, String password){

//        SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading news...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        RestClient client = new RestClient();
        client.getApiService().login(username, password,"UNIQANDROID","imc3bu","password", new Callback<AccessToken>() {
            @Override
            public void success(AccessToken s, Response response) {
                Uniq.getInstance().setAccessToken(s);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("test", "Log ouawdasd");
            }
        });
    }
}
