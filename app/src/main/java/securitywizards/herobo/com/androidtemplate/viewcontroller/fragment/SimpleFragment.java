package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidtemplate.securitywizards.herobo.com.androidtemplate.R;

/**
 * Fragment which houses the View pager.
 */
public class SimpleFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }


    public static Fragment newInstance(){
        SimpleFragment simpleFragment =new SimpleFragment();
        return simpleFragment;
    }
}