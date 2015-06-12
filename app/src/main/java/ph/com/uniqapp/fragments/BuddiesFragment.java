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
import ph.com.uniqapp.adapters.BuddyAdapter;
import ph.com.uniqapp.adapters.EventAdapter;
import ph.com.uniqapp.model.Event;
import ph.com.uniqapp.model.User;
import ph.com.uniqapp.utils.Base64;

/**
 * Created by cerberus on 6/12/15.
 */
public class BuddiesFragment extends BaseFragment {
    BuddyAdapter adapter;
    public BuddiesFragment() {

    }

    public static BuddiesFragment newInstance() {
        BuddiesFragment f = new BuddiesFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new BuddyAdapter(inflater, getContext());
        return inflater.inflate(R.layout.fragment_buddies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showBuddies();
    }

    public void showBuddies() {
        final SwipeRefreshLayout buddyLists = (SwipeRefreshLayout) getActivity().findViewById(R.id.buddySwipes);
        buddyLists.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buddyLists.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        ListView feedsList = (ListView) getActivity().findViewById(R.id.buddyList);
        for (int i = 0; i < 30; i++) {
           User user = new User();
            user.setFirst_name("Tester ");
            user.setLast_name(i + "");

            adapter.addItem(user);
        }
        feedsList.setAdapter(adapter);
    }
}
