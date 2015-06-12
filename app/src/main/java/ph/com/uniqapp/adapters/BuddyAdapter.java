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
import ph.com.uniqapp.model.User;

/**
 * Created by waelhe on 6/13/2015.
 */
public class BuddyAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<User> buddies;
    private Context context;

    public BuddyAdapter(LayoutInflater inflater, ArrayList<User> buddies, Context context) {
        this.inflater = inflater;
        this.buddies = buddies;
        this.context = context;
    }

    public BuddyAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
        this.buddies = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return buddies.size();
    }

    @Override
    public User getItem(int position) {
        return buddies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User buddy = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            Log.d("ViewHolder", "view holder is null");
            convertView = inflater.inflate(R.layout.item_buddy, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(buddy.getFirst_name() + " " + buddy.getLast_name());
        Picasso.with(context).load(R.drawable.troll).resize(100, 100).into(holder.image);
        return convertView;
    }

    public void addItem(User buddy) {
        buddies.add(buddy);
    }

    static class ViewHolder {

        public TextView name;
        public ImageView image;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.tv_name);
            this.image = (ImageView) view.findViewById(R.id.imageView);
        }

    }
}
