package ph.com.uniqapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

import org.joda.time.DateTime;
import org.w3c.dom.Text;

import java.util.ArrayList;

import ph.com.uniqapp.model.Category;
import ph.com.uniqapp.model.Event;
import ph.com.uniqapp.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by waelhe on 6/13/2015.
 */
public class NewPostActivity extends AppCompatActivity implements CalendarDatePickerDialog.OnDateSetListener, View.OnClickListener, RadialTimePickerDialog.OnTimeSetListener{
    TextView startDate;
    TextView endDate;
    CalendarDatePickerDialog dpb;
    FragmentManager fm;
    DateTime now;
    Spinner category;

    TextView currentTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("New Event");

        now = DateTime.now();
        fm = getSupportFragmentManager();
        dpb = CalendarDatePickerDialog
                .newInstance(NewPostActivity.this, now.getYear(), now.getMonthOfYear() - 1,
                        now.getDayOfMonth());

        startDate = (TextView) findViewById(R.id.tv_start_date);
        endDate = (TextView) findViewById(R.id.tv_end_date);

        category = (Spinner) findViewById(R.id.category);
        ArrayList<String> myCategories = new ArrayList<>();
        for (int i = 0; i < Uniq.getInstance().getMine().size(); i++) {
            Category cat = Uniq.getInstance().getMine().get(i);
            myCategories.add(cat.getName());
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myCategories); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(spinnerArrayAdapter);

        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);

        final EditText title = (EditText) findViewById(R.id.title),
                description = (EditText) findViewById(R.id.description),
                slug = (EditText) findViewById(R.id.slug),
                venue = (EditText) findViewById(R.id.venue);

        Button post = (Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event();
//                private String title;
//                private String description;
//                private String slug;
//                private String start_datetime;
//                private String end_datetime;
//                private String venue;
//                public int category_id;
//               /title"
//               /description"
//                slug"
//                venue
                event.setTitle(title.getText().toString());
                event.setDescription(description.getText().toString());
                event.setSlug(slug.getText().toString());
                event.setStart_date(startDate.getText().toString());
                event.setEnd_date(endDate.getText().toString());
                event.setVenue(venue.getText().toString());
                event.setCategoryId(Uniq.getInstance().getMine().get(category.getSelectedItemPosition()).getId());
                RestClient client = new RestClient();
                client.getApiService().postEvent(event, new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int i, int i1, int i2) {
        currentTextView.setText(i + "-" + i1 + "-" + i2);

        RadialTimePickerDialog timePickerDialog = RadialTimePickerDialog
                .newInstance(NewPostActivity.this, now.getHourOfDay(), now.getMinuteOfHour(),
                        DateFormat.is24HourFormat(NewPostActivity.this));
        timePickerDialog.setThemeDark(true);
        timePickerDialog.show(fm, "timePickerDialogFragment");
    }

    @Override
    public void onClick(View v) {
        currentTextView = (TextView) v;
        dpb.show(fm, "calendarDatePicker");
    }

    @Override
    public void onTimeSet(RadialTimePickerDialog radialTimePickerDialog, int i, int i1) {
        currentTextView.setText(currentTextView.getText() + " " + i + ":" + i1 + ":00");
    }
}
