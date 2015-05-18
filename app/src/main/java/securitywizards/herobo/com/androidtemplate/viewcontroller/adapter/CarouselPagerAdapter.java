

package securitywizards.herobo.com.androidtemplate.viewcontroller.adapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.DaimajaSliderFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.DatabaseListFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.ParallaxFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.SwipeFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.ButtonFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.MoviesFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.ProgressFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.SimpleFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.SimpleListFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.DialogsFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.FabFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.SliderFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.material.SnackbarFragment;
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
    List<FragmentGenerate> list=new ArrayList<FragmentGenerate>();
    public CarouselPagerAdapter(final Resources resources, final FragmentManager fragmentManager) {
        super(fragmentManager);
        this.resources = resources;
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return SimpleListFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Simple List";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return MoviesFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Movies";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return ButtonFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Buttons";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return DialogsFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Dialogs";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return FabFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "FAB";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return ProgressFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Progress";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return SliderFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Slider";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return SnackbarFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Snackbar";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return SpinnersFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Spinner";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return SwitchesFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Switches";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return TextfieldFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Textfield";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return ParallaxFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Parallax";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return SwipeFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Swipe";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return DaimajaSliderFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Daimaja Slider";
            }
        });
        list.add(new FragmentGenerate() {
            @Override
            public Fragment newInstance() {
                return DatabaseListFragment.newInstance();
            }
            @Override
            public String instanceName() {
                return "Database List";
            }
        });
    }


    private interface FragmentGenerate{
        abstract public Fragment newInstance();
        abstract public String instanceName();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(final int position) {
        return list.get(position).newInstance();
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return list.get(position).instanceName();
    }
}
