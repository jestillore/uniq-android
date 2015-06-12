package ph.com.uniqapp.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by cerberus on 6/12/15.
 */
public class BaseFragment extends Fragment {

    public Context getContext() {
        return getActivity().getApplicationContext();
    }

}
