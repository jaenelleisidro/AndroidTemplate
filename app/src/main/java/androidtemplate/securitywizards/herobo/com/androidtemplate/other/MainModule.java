package androidtemplate.securitywizards.herobo.com.androidtemplate.other;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import androidtemplate.securitywizards.herobo.com.androidtemplate.other.helper.AndroidUtils;
import androidtemplate.securitywizards.herobo.com.androidtemplate.other.helper.PostFromAnyThreadBus;
import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.MainActivity;
import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;
import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.CarouselFragment;
import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.NavigationDrawerFragment;
import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.SimpleFragment;
import androidtemplate.securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.SimpleListFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
 */
@Module(
        complete = false,

        injects = {
                MainApplication.class
                ,NavigationDrawerFragment.class
                , BaseFragment.class
                , CarouselFragment.class
                , SimpleFragment.class
                , SimpleListFragment.class
                , MainActivity.class



        }
)
public class MainModule {

    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new PostFromAnyThreadBus();
    }


    @Provides
    @Singleton
    AndroidUtils provideAndroidUtils(Context context){
        return new AndroidUtils(context);
    }


}
