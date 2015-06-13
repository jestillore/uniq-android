package ph.com.uniqapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import ph.com.uniqapp.R;
import ph.com.uniqapp.model.Category;
import ph.com.uniqapp.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cerberus on 6/13/15.
 */
public class CategoryFollowAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Category> categories;

    public CategoryFollowAdapter(LayoutInflater inflater, ArrayList<Category> categories) {
        this.inflater = inflater;
        this.categories = categories;
    }

    public CategoryFollowAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
        this.categories = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Category category = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_category_item_to_follow, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(category.getName());
        holder.description.setText(category.getDescription());
        holder.follow.setChecked(category.isFollowed());
        holder.follow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestClient client = new RestClient();
                if (isChecked) {
                    client.getApiService().follow(category.getId(), new Callback<Object>() {
                        @Override
                        public void success(Object o, Response response) {

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                }
                else {
                    client.getApiService().unfollow(category.getId(), new Callback<Object>() {
                        @Override
                        public void success(Object o, Response response) {

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                }
            }
        });
        return convertView;
    }

    public void addItem(Category category) {
        this.categories.add(category);
    }

    static class ViewHolder {

        public TextView name;
        public TextView description;
        public CheckBox follow;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.name);
            this.description = (TextView) view.findViewById(R.id.description);
            this.follow = (CheckBox) view.findViewById(R.id.follow);
        }

    }
}
