package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import securitywizards.herobo.com.androidtemplate.other.Injector;
import butterknife.Views;

/**
 * Created by jaenelleisidro on 5/16/15.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //inject via butterknife
        Views.inject(this, getView());
        //inject via dagger
        Injector.inject(this);

    }
}
