

package androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.adapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.SimpleFragment;
import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.SimpleListFragment;


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
        return 6;
    }

    @Override
    public Fragment getItem(final int position) {
        final Fragment result;
        switch (position) {
            case 0:
                result = SimpleFragment.newInstance();
                break;
            case 1:
                result = SimpleListFragment.newInstance();
                break;
            case 2:
                result = SimpleFragment.newInstance();
                break;
            case 3:
                result = SimpleFragment.newInstance();
                break;
            case 4:
                result = SimpleFragment.newInstance();
                break;
            case 5:
                result = SimpleFragment.newInstance();
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
