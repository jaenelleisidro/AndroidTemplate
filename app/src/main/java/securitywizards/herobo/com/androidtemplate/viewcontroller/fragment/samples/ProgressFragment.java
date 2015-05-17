package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.InjectView;
import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;

/**
 * Fragment which houses the View pager.
 */
public class ProgressFragment extends BaseFragment {
    @InjectView(R.id.progress_pv_circular)
    com.rey.material.widget.ProgressView progressPvCircular;
    @InjectView(R.id.progress_pv_circular_colors)
    com.rey.material.widget.ProgressView progressPvCircularColors;
    @InjectView(R.id.progress_pv_circular_inout)
    com.rey.material.widget.ProgressView progressPvCircularInout;
    @InjectView(R.id.progress_pv_circular_inout_colors)
    com.rey.material.widget.ProgressView progressPvCircularInoutColors;
    @InjectView(R.id.progress_pv_circular_determinate_in_out)
    com.rey.material.widget.ProgressView progressPvCircularDeterminateInOut;
    @InjectView(R.id.progress_pv_circular_determinate)
    com.rey.material.widget.ProgressView progressPvCircularDeterminate;

    @InjectView(R.id.progress_pv_linear)
    com.rey.material.widget.ProgressView progressPvLinear;
    @InjectView(R.id.progress_pv_linear_colors)
    com.rey.material.widget.ProgressView progressPvLinearColors;
    @InjectView(R.id.progress_pv_linear_determinate)
    com.rey.material.widget.ProgressView progressPvLinearDeterminate;
    @InjectView(R.id.progress_pv_linear_query)
    com.rey.material.widget.ProgressView progressPvLinearQuery;
    @InjectView(R.id.progress_pv_linear_buffer)
    com.rey.material.widget.ProgressView progressPvLinearBuffer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onActivityCreated2(Bundle savedInstanceState) {
        progressPvCircular.start();
        progressPvCircularColors.start();

        progressPvCircularInout.start();
        progressPvCircularInoutColors.start();

        progressPvCircularDeterminateInOut.start();
        progressPvCircularDeterminateInOut.setProgress(.5f);

        progressPvCircularDeterminate.start();
        progressPvCircularDeterminate.setProgress(.5f);


        progressPvLinear.start();
        progressPvLinear.setProgress(.5f);
        progressPvLinearColors.start();
        progressPvLinearColors.setProgress(.5f);
        progressPvLinearDeterminate.start();
//        progressPvLinearDeterminate.setProgress(.5f);
        progressPvLinearQuery.start();
//        progressPvLinearQuery.setProgress(.5f);
        progressPvLinearBuffer.start();
//        progressPvLinearBuffer.setProgress(.5f);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){}
    @Override
    public void onSaveInstanceState2(Bundle outState) {}


    public static Fragment newInstance(){
        ProgressFragment progressFragment =new ProgressFragment();
        return progressFragment;
    }
}