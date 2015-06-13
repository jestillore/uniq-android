package ph.com.uniqapp.authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ph.com.uniqapp.R;
import ph.com.uniqapp.model.User;
import ph.com.uniqapp.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by waelhe on 6/12/2015.
 */
public class RegisterActivity extends AppCompatActivity{
    EditText username;
    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;
    EditText confirm_password;
    Button signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Registration");

        username = (EditText) findViewById(R.id.et_username);
        first_name = (EditText) findViewById(R.id.et_firstname);
        last_name = (EditText) findViewById(R.id.et_lastname);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_pasword);
        confirm_password = (EditText) findViewById(R.id.et_confirmation_password);
        signup = (Button) findViewById(R.id.btn_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RestClient().getApiService().register(username.getText().toString(), first_name.getText().toString(), last_name.getText().toString(), email.getText().toString(), password.getText().toString(), confirm_password.getText().toString(), new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        Log.d("test", "test" + s);
                        finish();
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.e("test", "test");
                        Toast.makeText(RegisterActivity.this, "Registration failed !" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
