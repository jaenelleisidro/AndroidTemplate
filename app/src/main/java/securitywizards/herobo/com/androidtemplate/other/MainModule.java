package securitywizards.herobo.com.androidtemplate.other;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import securitywizards.herobo.com.androidtemplate.model.businesslayer.MovieService;
import securitywizards.herobo.com.androidtemplate.model.dataaccesslayer.MovieHttpService;
import securitywizards.herobo.com.androidtemplate.other.helper.AndroidUtils;
import securitywizards.herobo.com.androidtemplate.other.helper.PostFromAnyThreadBus;
import securitywizards.herobo.com.androidtemplate.viewcontroller.activity.MainActivity;
import securitywizards.herobo.com.androidtemplate.viewcontroller.activity.MovieActivity;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.CarouselFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.ButtonFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.MoviesFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.NavigationDrawerFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.ParallaxFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.ProgressFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.SimpleFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.SimpleListFragment;
import dagger.Module;
import dagger.Provides;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples.SwipeFragment;
import securitywizards.herobo.com.androidtemplate.viewcontroller.service.DownloadService;

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
                , MoviesFragment.class
                , ButtonFragment.class
                , ProgressFragment.class
                , DownloadService.class
                , MainActivity.class
                , ParallaxFragment.class
                , SwipeFragment.class
                ,MovieActivity.class
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

    @Provides
    Gson provideGson() {
        /**
         * GSON instance to use for all request  with date format set up for proper parsing.
         * <p/>
         * You can also configure GSON with different naming policies for your API.
         * Maybe your API is Rails API and all json values are lower case with an underscore,
         * like this "first_name" instead of "firstName".
         * You can configure GSON as such below.
         * <p/>
         *
         * public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd")
         *         .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
         */
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }


    @Provides
    RestAdapterRequestInterceptor provideRestAdapterRequestInterceptor(UserAgentProvider userAgentProvider,AndroidUtils androidUtils) {
        return new RestAdapterRequestInterceptor(userAgentProvider,androidUtils);
    }
    @Provides
    RestErrorHandler provideRestErrorHandler(Bus bus,RestAdapterRequestInterceptor restAdapterRequestInterceptor) {
        return new RestErrorHandler(bus,restAdapterRequestInterceptor);
    }

    @Provides
    OkHttpClient provideRestAdapterRequestInterceptor(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        int cacheSize = 50 * 1024 * 1024; // 10 MiB
        File cacheDirectory = new File(context.getCacheDir().getAbsolutePath(), "HttpCache");
        try {
            Cache cache = new Cache(cacheDirectory, cacheSize);
            okHttpClient.setCache(cache);
        } catch (IOException e) {
            //cache is not really important, skip if something went wrong
        }
        return okHttpClient;
    }



    @Provides
    RestAdapter provideRestAdapter(Context context,RestErrorHandler restErrorHandler, RestAdapterRequestInterceptor restRequestInterceptor, Gson gson,OkHttpClient okHttpClient) {
        return new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(MovieHttpService.URL_MOVIEWEBSITE)
                .setErrorHandler(restErrorHandler)
                .setRequestInterceptor(restRequestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Singleton
    @Provides
    MovieHttpService provideProductHttpService(RestAdapter restAdapter){
        return restAdapter.create(MovieHttpService.class);
    }

    @Singleton
    @Provides
    MovieService provideMovieService(MovieHttpService movieHttpService,AndroidUtils androidUtils){
        return new MovieService(movieHttpService,androidUtils);
    }
}
