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
public class NotificationsFragment extends BaseFragment {

    public NotificationsFragment() {

    }

    public static NotificationsFragment newInstance() {
        NotificationsFragment f = new NotificationsFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
