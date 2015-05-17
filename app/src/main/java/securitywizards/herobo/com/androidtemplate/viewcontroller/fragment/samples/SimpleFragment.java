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
public class SimpleFragment extends BaseFragment {

    public int layoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        onRestoreInstanceState(savedInstanceState);
        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //Here you can restore saved data in onSaveInstanceState Bundle
    private void onRestoreInstanceState(Bundle savedInstanceState){
        if(savedInstanceState!=null){
            layoutId=savedInstanceState.getInt("layoutId");
        }
    }

    //Here you Save your data
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("layoutId", layoutId);
    }


    public static Fragment newInstance(){
        return newInstance(R.layout.fragment_button);
    }

    public static Fragment newInstance(int layoutId){
        SimpleFragment simpleFragment =new SimpleFragment();
        simpleFragment.layoutId=layoutId;
        simpleFragment.setRetainInstance(true);
        return simpleFragment;
    }
}