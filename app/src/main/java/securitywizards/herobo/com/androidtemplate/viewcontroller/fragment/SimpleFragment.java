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

    public static final String ARG_LAYOUTID = "layoutid";
    public int layoutId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(layoutId, container, false);
    }

    public static Fragment newInstance(){
        return newInstance(R.layout.fragment_button);
    }

    public static Fragment newInstance(int layoutId){
        SimpleFragment simpleFragment =new SimpleFragment();
        simpleFragment.layoutId=layoutId;
        return simpleFragment;
    }
}