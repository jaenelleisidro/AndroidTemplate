package securitywizards.herobo.com.androidtemplate.other;

import com.squareup.otto.Bus;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class RestErrorHandler implements ErrorHandler {

    public static final int HTTP_NOT_FOUND = 404;
    public static final int INVALID_LOGIN_PARAMETERS = 101;

    private Bus bus;

    public RestErrorHandler(Bus bus) {
        this.bus = bus;
    }

    @Override
    public Throwable handleError(RetrofitError cause) {
        if(cause != null) {
            //TODO : do something about the retrofit error, maybe send something via bus to udpate ui
        }
        return cause;
    }


}
