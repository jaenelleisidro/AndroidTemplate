package securitywizards.herobo.com.androidtemplate.other;

import com.squareup.otto.Bus;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class RestErrorHandler implements ErrorHandler {

    public static final int HTTP_NOT_FOUND = 404;
    public static final int INVALID_LOGIN_PARAMETERS = 101;

    private Bus bus;
    private RestAdapterRequestInterceptor restAdapterRequestInterceptor;
    public RestErrorHandler(Bus bus,RestAdapterRequestInterceptor restAdapterRequestInterceptor) {
        this.bus = bus;
        this.restAdapterRequestInterceptor=restAdapterRequestInterceptor;
    }

    @Override
    public Throwable handleError(RetrofitError cause) {
            //TODO : do something about the retrofit error, maybe send something via bus to udpate ui

        if(cause != null) {
            switch (cause.getResponse().getStatus()) {
                case 404:
                    restAdapterRequestInterceptor.useCacheForNow();
                break;
            }
        }
        return cause;
    }


}
