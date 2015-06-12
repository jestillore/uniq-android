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

import ph.com.uniqapp.EventActivity;
import ph.com.uniqapp.R;
import ph.com.uniqapp.adapters.EventAdapter;
import ph.com.uniqapp.model.Event;
import ph.com.uniqapp.utils.Base64;

/**
 * Created by cerberus on 6/12/15.
 */
public class MyPostsFragment extends BaseFragment {

    EventAdapter adapter;

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
        return inflater.inflate(R.layout.fragment_my_posts, container, false);
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
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setTitle("My Event " + (i + 1));
            event.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            event.setStart_date("2015-06-12 18:00:00");
            event.setEnd_date("2015-06-13 15:30:00");
            event.setSlug("my-event-" + (i + 1));
            event.setVenue("The TIDE Cebu");
            adapter.addItem(event);
        }
        myPostsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                Event event = adapter.getItem(position);
                try {
                    intent.putExtra("event", Base64.encodeObject(event));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
        myPostsList.setAdapter(adapter);
    }
}
