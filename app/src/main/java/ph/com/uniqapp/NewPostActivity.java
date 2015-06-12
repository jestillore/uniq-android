package ph.com.uniqapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

import org.joda.time.DateTime;
import org.w3c.dom.Text;

/**
 * Created by waelhe on 6/13/2015.
 */
public class NewPostActivity extends AppCompatActivity implements CalendarDatePickerDialog.OnDateSetListener, View.OnClickListener, RadialTimePickerDialog.OnTimeSetListener{
    TextView startDate;
    TextView endDate;
    CalendarDatePickerDialog dpb;
    FragmentManager fm;
    DateTime now;

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

        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);

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
        currentTextView.setText(i + "/" + i1 + "/" + i2);

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
        currentTextView.setText(currentTextView.getText() + " " + i + " " + i1);
    }
}
