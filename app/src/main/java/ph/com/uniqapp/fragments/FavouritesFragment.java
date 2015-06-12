package ph.com.uniqapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ph.com.uniqapp.R;

/**
 * Created by cerberus on 6/12/15.
 */
public class FavouritesFragment extends BaseFragment {

    public FavouritesFragment() {

    }

    public static FavouritesFragment newInstance() {
        FavouritesFragment f = new FavouritesFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
