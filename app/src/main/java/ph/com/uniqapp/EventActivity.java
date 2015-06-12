package ph.com.uniqapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;

import ph.com.uniqapp.adapters.CommentAdapter;
import ph.com.uniqapp.model.Comment;
import ph.com.uniqapp.model.Event;
import ph.com.uniqapp.utils.Base64;

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
        CommentAdapter adapter = new CommentAdapter(getLayoutInflater(),getApplicationContext());
        for(int i = 0 ; i < 10 ; i ++){
            Comment comment = new Comment();
            adapter.addItem(comment);
        }
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Event event = null;
        try {
            event = (Event) Base64.decodeToObject(getIntent().getExtras().getString("event"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setTitle(event.getTitle());


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
