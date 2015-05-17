package securitywizards.herobo.com.androidtemplate.viewcontroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonsware.cwac.endless.EndlessAdapter;

import java.util.ArrayList;

import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.domain.Movie;
import securitywizards.herobo.com.androidtemplate.domain.Movies;
import securitywizards.herobo.com.androidtemplate.model.businesslayer.MovieService;

public class MovieAdapter extends EndlessAdapter {
  private RotateAnimation rotate=null;
  private View pendingView=null;
  private MovieService movieService;



  public MovieAdapter(Context ctxt, MovieService movieService) {
    this(ctxt,new ArrayList<Movie>(),movieService);
  }

  public MovieAdapter(Context ctxt, ArrayList<Movie> list, MovieService movieService) {
    super(new ArrayAdapter<Movie>(ctxt,
                                    R.layout.adapter_simplelist_row,
                                    R.id.label,
                                    list));
    this.movieService=movieService;
    rotate=new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF,
                                0.5f);
    rotate.setDuration(1000);
    rotate.setRepeatMode(Animation.RESTART);
    rotate.setRepeatCount(Animation.INFINITE);
  }
  
  @Override
  protected View getPendingView(ViewGroup parent) {
    View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_simplelist_row, null);
    
    pendingView=row.findViewById(R.id.label);
    pendingView.setVisibility(View.GONE);
    pendingView=row.findViewById(R.id.icon);
    pendingView.setVisibility(View.VISIBLE);
    startProgressAnimation();
    
    return(row);
  }    @Override
       public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        View rowView=null;
        if(convertView==null){
            rowView=super.getView(position,convertView,parent);
            viewHolder=new ViewHolder();
            viewHolder.textView = (TextView) rowView.findViewById(R.id.label);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(viewHolder);
        }else{
            rowView=convertView;
            viewHolder=(ViewHolder)rowView.getTag();
        }

        if(position<getWrappedAdapter().getCount()) {
            Movie movie = (Movie) getWrappedAdapter().getItem(position);
            viewHolder.textView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.textView.setText(position+":"+movie.name);
        }
        return rowView;
    }




  volatile Movies movies;

    /**
     * returns true if there's still data that can be fetched
     * @return
     */
  @Override
  protected boolean cacheInBackground() {
    //to test for slow netwrok try this -> SystemClock.sleep(10000);
    try {
        movies = movieService.getMovie(getWrappedAdapter().getCount());
        return (getWrappedAdapter().getCount() < movies.count);
    }catch(RuntimeException e2){
    }catch(Exception e){
    }
      return true;
  }
  
  @Override
  protected void appendCachedData() {
    if (movies!=null && getWrappedAdapter().getCount()< movies.count) {
      ArrayAdapter<Movie> a=(ArrayAdapter<Movie>)getWrappedAdapter();
      //if you need to find the index.. a.getCount()-1
      for(Movie movie:movies.results){
          a.add(movie);
      }
    }
  }
  
  void startProgressAnimation() {
    if (pendingView!=null) {
      pendingView.startAnimation(rotate);
    }
  }

    private class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

}