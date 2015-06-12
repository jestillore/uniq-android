package ph.com.uniqapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import ph.com.uniqapp.R;
import ph.com.uniqapp.adapters.EventAdapter;
import ph.com.uniqapp.model.Event;

/**
 * Created by cerberus on 6/12/15.
 */
public class HomeFragment extends BaseFragment {

    private EventAdapter adapter;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new EventAdapter(inflater, getContext());
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.showFeeds();
    }

    public void showFeeds() {
        final SwipeRefreshLayout homeFeeds = (SwipeRefreshLayout) getActivity().findViewById(R.id.homeFeeds);
        homeFeeds.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homeFeeds.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        ListView feedsList = (ListView) getActivity().findViewById(R.id.feedsList);
        for (int i = 0; i < 50; i++) {
            adapter.addItem(new Event());
        }
        feedsList.setAdapter(adapter);
    }
}
