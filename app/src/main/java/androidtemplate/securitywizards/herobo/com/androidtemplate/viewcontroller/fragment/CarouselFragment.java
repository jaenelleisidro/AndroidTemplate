package androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TitlePageIndicator;

import androidtemplate.securitywizards.herobo.com.androidtemplate.R;
import androidtemplate.securitywizards.herobo.com.androidtemplate.other.Injector;
import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.adapter.CarouselPagerAdapter;
import butterknife.InjectView;
import butterknife.Views;

/**
 * Fragment which houses the View pager.
 */
public class CarouselFragment extends BaseFragment {

    @InjectView(R.id.tpi_header)
    protected TitlePageIndicator indicator;

    @InjectView(R.id.vp_pages)
    protected ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carousel, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pager.setAdapter(new CarouselPagerAdapter(getResources(), getChildFragmentManager()));
        indicator.setViewPager(pager);
        pager.setCurrentItem(0);

    }
}