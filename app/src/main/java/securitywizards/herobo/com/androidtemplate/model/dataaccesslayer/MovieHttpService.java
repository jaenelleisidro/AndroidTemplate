package securitywizards.herobo.com.androidtemplate.model.dataaccesslayer;


import retrofit.http.GET;
import retrofit.http.Query;
import securitywizards.herobo.com.androidtemplate.domain.Movies;

public interface MovieHttpService {
    public static final String URL_MOVIEWEBSITE ="http://whatsbeef.net";
    public static final String URL_GETMOVIES= "/wabz/guide.php";

    public static final String PARAM_PRODUCTLIST_START="start";


    @GET(URL_GETMOVIES)
    Movies searchProducts(
            @Query(PARAM_PRODUCTLIST_START) int start);

}
