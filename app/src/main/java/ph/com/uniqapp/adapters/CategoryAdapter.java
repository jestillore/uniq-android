package ph.com.uniqapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import ph.com.uniqapp.R;
import ph.com.uniqapp.model.Category;

/**
 * Created by cerberus on 6/13/15.
 */
public class CategoryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Category> categories;

    public CategoryAdapter(LayoutInflater inflater, ArrayList<Category> categories) {
        this.inflater = inflater;
        this.categories = categories;
    }

    public CategoryAdapter(LayoutInflater inflater) {
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
        Category category = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_item_category, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(category.getName());
        return convertView;
    }

    public void addItem(Category category) {
        this.categories.add(category);
    }

    static class ViewHolder {

        public TextView name;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.name);
        }

    }
}
