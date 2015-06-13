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
public class FavouritesFragment extends BaseFragment {

    EventAdapter adapter;

    public FavouritesFragment() {

    }

    public static FavouritesFragment newInstance() {
        FavouritesFragment f = new FavouritesFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new EventAdapter(inflater, getContext());
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.showFavourites();
    }

    public void showFavourites() {
        final SwipeRefreshLayout favourites = (SwipeRefreshLayout) getActivity().findViewById(R.id.favourites);
        favourites.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        favourites.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        ListView favouritesList = (ListView) getActivity().findViewById(R.id.favouritesList);
        favouritesList.setAdapter(adapter);
        RestClient client = new RestClient();
        client.getApiService().getFavourites(new Callback<ArrayList<Event>>() {
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
        favouritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                Event event = adapter.getItem(position);
                Uniq.getInstance().setCurrentEvent(event);
                startActivity(intent);
            }
        });
    }
}
