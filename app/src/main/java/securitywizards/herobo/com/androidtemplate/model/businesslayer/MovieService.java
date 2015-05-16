package securitywizards.herobo.com.androidtemplate.model.businesslayer;

import java.util.List;

import securitywizards.herobo.com.androidtemplate.domain.Movie;
import securitywizards.herobo.com.androidtemplate.domain.Movies;
import securitywizards.herobo.com.androidtemplate.model.dataaccesslayer.MovieHttpService;
import securitywizards.herobo.com.androidtemplate.other.helper.AndroidUtils;

/**
 * Created by Android 17 on 5/8/2015.
 */
public class MovieService {
    private final MovieHttpService movieHttpService;
    private final AndroidUtils androidUtils;

    public MovieService(MovieHttpService movieHttpService, AndroidUtils androidUtils) {
        this.movieHttpService=movieHttpService;
        this.androidUtils=androidUtils;
    }

    public Movies getMovie(int start){
        Movies movies=movieHttpService.searchProducts(start);
        return movies;
    }

}
