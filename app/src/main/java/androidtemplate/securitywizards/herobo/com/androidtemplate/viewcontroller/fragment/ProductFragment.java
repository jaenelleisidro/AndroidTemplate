package androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidtemplate.securitywizards.herobo.com.androidtemplate.R;
import butterknife.Views;

/**
 * Fragment which houses the View pager.
 */
public class ProductFragment extends Fragment {

    int type=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Views.inject(this, getView());
    }


    public static Fragment newInstance(){
        ProductFragment productFragment=new ProductFragment();
        return productFragment;
    }
}