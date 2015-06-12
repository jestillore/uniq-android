package ph.com.uniqapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ph.com.uniqapp.R;
import ph.com.uniqapp.model.Event;

/**
 * Created by cerberus on 6/13/15.
 */
public class EventAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Event> events;
    private Context context;

    public EventAdapter(LayoutInflater inflater, ArrayList<Event> events, Context context) {
        this.inflater = inflater;
        this.events = events;
        this.context = context;
    }

    public EventAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
        this.events = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Event getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            Log.d("ViewHolder", "view holder is null");
            convertView = inflater.inflate(R.layout.row_event_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(R.drawable.troll).resize(100, 100).into(holder.image);
        return convertView;
    }

    public void addItem(Event event) {
        events.add(event);
    }

    static class ViewHolder {

        public TextView name;
        public TextView date;
        public TextView location;
        public TextView category;
        public ImageView image;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.name);
            this.date = (TextView) view.findViewById(R.id.date);
            this.location = (TextView) view.findViewById(R.id.location);
            this.category = (TextView) view.findViewById(R.id.category);
            this.image = (ImageView) view.findViewById(R.id.image);
        }

    }
}
