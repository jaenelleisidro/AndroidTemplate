package securitywizards.herobo.com.androidtemplate.viewcontroller.activity;


import android.content.Context;

import android.content.Intent;

import android.os.Bundle;

import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import securitywizards.herobo.com.androidtemplate.R;
import butterknife.InjectView;
import securitywizards.herobo.com.androidtemplate.domain.Movie;


public class MovieActivity extends BaseActivity {
    public static final String KEY_MOVIE="KEY_MOVIE";



    @InjectView(R.id.tvTitle)
    protected TextView tvTitle;
    @InjectView(R.id.tvDetails)
    protected TextView tvDetails;
    @InjectView(R.id.imgIcon)
    protected ImageView imgIcon;

    @Inject
    Bus eventBus;

    private Movie movie;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus.register(this);

        setContentView(R.layout.activity_product);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movie = (Movie) getIntent().getSerializableExtra(KEY_MOVIE);
        tvTitle.setText(movie.name);
        String html="channel : "+ movie.channel+"<br/>"
                +"End Time : " + movie.end_time+"<br/>"
                +"Start Time : " + movie.start_time+"<br/>"
                +"Rating : " + movie.rating+"<br/>";

        tvDetails.setText(Html.fromHtml(html));
    }

    public static void start(Context context,Movie movie){
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(KEY_MOVIE, movie);
        context.startActivity(intent);
    }
}
