package ph.com.uniqapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ph.com.uniqapp.R;
import ph.com.uniqapp.adapters.CategoryAdapter;
import ph.com.uniqapp.model.Category;
import ph.com.uniqapp.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cerberus on 6/13/15.
 */
public class CategoriesFragment extends BaseFragment {

    CategoryAdapter adapter;

    public CategoriesFragment() {

    }

    public static CategoriesFragment newInstance() {
        CategoriesFragment f = new CategoriesFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new CategoryAdapter(inflater);
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.showCategories(view);
    }

    public void showCategories(View view) {
        final ListView categories = (ListView) view.findViewById(R.id.categories);
        categories.setAdapter(adapter);
        RestClient client = new RestClient();
        client.getApiService().getAllCategories(new Callback<ArrayList<Category>>() {
            @Override
            public void success(ArrayList<Category> cats, Response response) {
                for (Category category : cats) {
                    adapter.addItem(category);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
