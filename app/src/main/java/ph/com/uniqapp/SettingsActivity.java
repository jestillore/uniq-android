package ph.com.uniqapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import ph.com.uniqapp.adapters.CategoryFollowAdapter;
import ph.com.uniqapp.model.Category;
import ph.com.uniqapp.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cerberus on 6/13/15.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView categories = (ListView) findViewById(R.id.categories);
        final CategoryFollowAdapter adapter = new CategoryFollowAdapter(getLayoutInflater());
        categories.setAdapter(adapter);
        RestClient client = new RestClient();
        client.getApiService().getAllCategories(new Callback<ArrayList<Category>>() {
            @Override
            public void success(ArrayList<Category> cats, Response response) {
                for (Category category : cats) {
                    adapter.addItem(category);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

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
