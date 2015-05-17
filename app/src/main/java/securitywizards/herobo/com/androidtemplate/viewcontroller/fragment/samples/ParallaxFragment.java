package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;

/**
 * Fragment which houses the View pager.
 */
public class ParallaxFragment extends BaseFragment {

    public int layoutId;

    @Override
    public View onCreateView2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parallax, container, false);
    }

    @Override
    public void onActivityCreated2(Bundle savedInstanceState) {
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        layoutId=savedInstanceState.getInt("layoutId");
    }

    //Here you Save your data
    @Override
    public void onSaveInstanceState2(Bundle outState) {
        outState.putInt("layoutId", layoutId);
    }


    public static ParallaxFragment newInstance(){
        ParallaxFragment parallaxFragment =new ParallaxFragment();
        return parallaxFragment;
    }
}