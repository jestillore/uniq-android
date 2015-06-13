package ph.com.uniqapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import ph.com.uniqapp.adapters.CommentAdapter;
import ph.com.uniqapp.model.Comment;
import ph.com.uniqapp.model.Event;
import ph.com.uniqapp.rest.RestClient;
import ph.com.uniqapp.utils.Base64;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cerberus on 6/13/15.
 */
public class EventActivity extends AppCompatActivity {
    ListView listView;
    View header;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.event_list);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = inflater.inflate(R.layout.item_event_header, null, false);
        listView.addHeaderView(header);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Event event = Uniq.getInstance().getCurrentEvent();
        setTitle(event.getTitle());

        ((TextView) header.findViewById(R.id.tv_event_title)).setText(event.getTitle());
        ((TextView) header.findViewById(R.id.tv_venue)).setText(event.getVenue());
        ((TextView) header.findViewById(R.id.tv_category)).setText(event.getCategory().getName());
        ((TextView) header.findViewById(R.id.tv_start_end_date)).setText(event.getStart_date() + " - " + event.getEnd_date());
        ((TextView) header.findViewById(R.id.tv_description)).setText(event.getDescription());

        final RestClient client = new RestClient();
        client.getApiService().getComments(event.getId(), new Callback<ArrayList<Comment>>() {
            @Override
            public void success(ArrayList<Comment> comments, Response response) {
                CommentAdapter adapter = new CommentAdapter(getLayoutInflater(),getApplicationContext());
                for (Comment comment : comments) {
                    adapter.addItem(comment);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Failed to fetch comments.", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_comment = (Button) findViewById(R.id.btn_comment);
        final EditText et_comment = (EditText) findViewById(R.id.et_comment);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.getApiService().postComment(event.getId(), et_comment.getText().toString(), new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        Toast.makeText(getApplicationContext(), "Post comment successful.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), "Failed to post comment.", Toast.LENGTH_SHORT).show();
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
