

package securitywizards.herobo.com.androidtemplate.viewcontroller.adapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.ParallaxFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.ButtonFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.MoviesFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.ProgressFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.SimpleFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.SimpleListFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.DialogsFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.FabFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.SliderFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.SpinnersFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.SwitchesFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.TextfieldFragment;


/**
 * Pager adapter
 */
public class CarouselPagerAdapter extends FragmentPagerAdapter {

    private final Resources resources;

    /**
     * Create pager adapter
     *
     * @param resources
     * @param fragmentManager
     */
    public CarouselPagerAdapter(final Resources resources, final FragmentManager fragmentManager) {
        super(fragmentManager);
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public Fragment getItem(final int position) {
        final Fragment result;
        switch (position) {
            case 0:
                result = SimpleListFragment.newInstance();
                break;
            case 1:
                result = MoviesFragment.newInstance();
                break;
            case 2:
                result = ButtonFragment.newInstance();
                break;
            case 3:
                result = DialogsFragment.newInstance();
                break;
            case 4:
                result = FabFragment.newInstance();
                break;
            case 5:
                result = ProgressFragment.newInstance();
                break;
            case 6:
                result = SliderFragment.newInstance();
                break;
            case 7:
                result = SimpleFragment.newInstance(R.layout.fragment_snackbar);
                break;
            case 8:
                result = SpinnersFragment.newInstance();
                break;
            case 9:
                result = SwitchesFragment.newInstance();
                break;
            case 10:
                result = TextfieldFragment.newInstance();
                break;
            case 11:
                result = ParallaxFragment.newInstance();
                break;
            default:
                result = null;
                break;
        }
        if (result != null) {
            result.setArguments(new Bundle()); //TODO do we need this?
        }
        return result;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return "page title "+position;
//        switch (position) {
//            case 0:
//                return resources.getString(R.string.page_home);
//            case 1:
//                return resources.getString(R.string.page_free);
//            case 2:
//                return resources.getString(R.string.page_paid);
//            case 3:
//                return resources.getString(R.string.page_hot);
//            case 4:
//                return resources.getString(R.string.page_staffpic);
//            case 5:
//                return resources.getString(R.string.page_recommended);
//            default:
//                return null;
//        }
    }
}
