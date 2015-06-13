package ph.com.uniqapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import ph.com.uniqapp.EventActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import ph.com.uniqapp.NewPostActivity;
import ph.com.uniqapp.R;
import ph.com.uniqapp.Uniq;
import ph.com.uniqapp.adapters.EventAdapter;
import ph.com.uniqapp.model.Event;
import ph.com.uniqapp.rest.RestClient;
import ph.com.uniqapp.utils.Base64;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cerberus on 6/12/15.
 */
public class MyPostsFragment extends BaseFragment {

    EventAdapter adapter;
    View view;
    TextView startDate;
    TextView endStart;
    public MyPostsFragment() {

    }

    public static MyPostsFragment newInstance() {
        MyPostsFragment f = new MyPostsFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new EventAdapter(inflater, getContext());
        view = inflater.inflate(R.layout.fragment_my_posts, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewPostActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.showMyPosts();
    }

    public void showMyPosts() {
        final SwipeRefreshLayout myPosts = (SwipeRefreshLayout) getActivity().findViewById(R.id.myPosts);
        myPosts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myPosts.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        ListView myPostsList = (ListView) getActivity().findViewById(R.id.myPostsList);
        myPostsList.setAdapter(adapter);
        RestClient client = new RestClient();
        client.getApiService().getMyEvents(new Callback<ArrayList<Event>>() {
            @Override
            public void success(ArrayList<Event> events, Response response) {
                for (Event event : events) {
                    adapter.addItem(event);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        myPostsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                Event event = adapter.getItem(position);
                Uniq.getInstance().setCurrentEvent(event);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.attachToListView(myPostsList);
    }
}
