package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import securitywizards.herobo.com.androidtemplate.R;
import butterknife.InjectView;
import butterknife.Views;
import securitywizards.herobo.com.androidtemplate.other.Injector;
import securitywizards.herobo.com.androidtemplate.other.helper.AndroidUtils;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.service.DownloadService;

/**
 * Fragment which houses the View pager.
 */
public class ButtonFragment extends BaseFragment {

    public static final String ARG_LAYOUTID = "layoutid";
    public int layoutId;
    @InjectView(R.id.button_bt_flat)
    com.rey.material.widget.Button btnBtFlat;
    @Inject
    AndroidUtils androidUtils;

    @Override
    public View onCreateView2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_button, container, false);
    }

    @Override
    public void onActivityCreated2(Bundle savedInstanceState) {
        btnBtFlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidUtils.alert("lol");
                DownloadService.startDownload(getActivity(),"http://hcmaslov.d-real.sci-nnov.ru/public/mp3/Beatles/11%20Yellow%20Submarine/The%20Beatles%20-%20Yellow%20Submarine%20-%2001%20Yellow%20Submarine.mp3",androidUtils.getDefaultDownloadLocation("temp.mp3").getAbsolutePath());
            }
        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){}
    @Override
    public void onSaveInstanceState2(Bundle outState) {}



    public static ButtonFragment newInstance(){
        ButtonFragment buttonFragment =new ButtonFragment();
        return buttonFragment;
    }
}