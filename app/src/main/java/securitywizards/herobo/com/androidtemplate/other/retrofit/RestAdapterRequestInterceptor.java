package securitywizards.herobo.com.androidtemplate.other.retrofit;



import retrofit.RequestInterceptor;
import securitywizards.herobo.com.androidtemplate.other.helper.AndroidUtils;

public class RestAdapterRequestInterceptor implements RequestInterceptor {

    private UserAgentProvider userAgentProvider;
    private AndroidUtils androidUtils;
    public RestAdapterRequestInterceptor(UserAgentProvider userAgentProvider,AndroidUtils androidUtils) {
        this.userAgentProvider = userAgentProvider;
        this.androidUtils=androidUtils;
    }

    /**
     * will be called when connected to a network without internet
     */
    boolean useCacheForNow=false;
    long useCacheStart=0;
    long useCacheElapsedBeforeExpire=1000*60*3;//retry fetching data after 3 minutes
    public void useCacheForNow(){
        useCacheForNow=true;
        useCacheStart=System.currentTimeMillis();
    }

    @Override
    public void intercept(RequestFacade request) {

        // Add header to set content type of JSON
        request.addHeader("Content-Type", "application/json");

        // Add the user agent to the request.
        request.addHeader("User-Agent", userAgentProvider.get());

        request.addHeader("Accept", "application/json;versions=1");
        if (useCacheForNow==false && androidUtils.isNetworkAvailable()) {
            int maxAge = 60; // read from cache for 1 minute
            request.addHeader("Cache-Control", "public, max-age=" + maxAge);
        } else {
            if(useCacheForNow) {
                if(System.currentTimeMillis()-useCacheStart<useCacheElapsedBeforeExpire) {
                    useCacheForNow = false;
                }
            }
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            request.addHeader("Cache-Control",
                    "public, only-if-cached, max-stale=" + maxStale);
        }
    }
}
