package ph.com.uniqapp.authentication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import ph.com.uniqapp.R;

/**
 * Created by waelhe on 6/12/2015.
 */
public class LoginActivity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
    }
}
