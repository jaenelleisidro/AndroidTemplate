package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import securitywizards.herobo.com.androidtemplate.other.dagger.Injector;

/**
 * Created by jaenelleisidro on 5/16/15.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         this throws an error when nested fragment
//        setRetainInstance(true); //Will ignore onDestroy Method (Nested Fragments no need this if parent have it)
    }





    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //inject via butterknife
        ButterKnife.inject(this, getView());
        //inject via dagger
        Injector.inject(this);
        onActivityCreated2(savedInstanceState);
    }

    abstract public void onActivityCreated2(Bundle savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState!=null) {
            onRestoreInstanceState(savedInstanceState);
        }
        return onCreateView2(inflater,container,savedInstanceState);
    }
    abstract public View onCreateView2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    //Here you can restore saved data in onSaveInstanceState Bundle
    abstract public void onRestoreInstanceState(Bundle savedInstanceState);

    //Here you Save your data
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSaveInstanceState2(outState);
    }
    abstract public void onSaveInstanceState2(Bundle outState);

}
