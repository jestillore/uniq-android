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
import ph.com.uniqapp.model.Comment;

/**
 * Created by waelhe on 6/13/2015.
 */
public class CommentAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<Comment> comments;
    private Context context;

    public CommentAdapter(LayoutInflater inflater, ArrayList<Comment> comments, Context context) {
        this.inflater = inflater;
        this.comments = comments;
        this.context = context;
    }

    public CommentAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
        this.comments = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            Log.d("ViewHolder", "view holder is null");
            convertView = inflater.inflate(R.layout.item_comment, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText("Test");
        holder.comment.setText("awafs");
        holder.date.setText("awafs");
        Picasso.with(context).load(R.drawable.troll).resize(100, 100).into(holder.image);
        return convertView;
    }

    public void addItem(Comment commen) {
        comments.add(commen);
    }

    static class ViewHolder {

        public TextView name;
        public TextView comment;
        public TextView date;
        public ImageView image;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.tv_name);
            this.comment = (TextView) view.findViewById(R.id.tv_comment);
            this.date = (TextView) view.findViewById(R.id.tv_date);
            this.image = (ImageView) view.findViewById(R.id.iv_image);
        }

    }
}
