package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import javax.inject.Inject;

import androidtemplate.securitywizards.herobo.com.androidtemplate.R;
import butterknife.InjectView;
import butterknife.Views;
import securitywizards.herobo.com.androidtemplate.domain.Movie;
import securitywizards.herobo.com.androidtemplate.model.businesslayer.MovieService;
import securitywizards.herobo.com.androidtemplate.other.Injector;
import securitywizards.herobo.com.androidtemplate.viewcontroller.activity.MovieActivity;
import securitywizards.herobo.com.androidtemplate.viewcontroller.adapter.MovieAdapter;

public class MoviesFragment extends BaseFragment {

    @Inject
    protected MovieService movieService;

    @InjectView(R.id.lvListHolder)
    protected ListView lvMovies;
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simplelist, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Views.inject(this, getView());
        //MoviesAdapter adapter=new MoviesAdapter(getActivity(),movieService);
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int ctr=1;ctr<10;ctr++){
            list.add(ctr);
        }

//        DemoAdapter adapter=new DemoAdapter(getActivity(),list);
        MovieAdapter adapter=new MovieAdapter(getActivity(),movieService);
        lvMovies.setAdapter(adapter);
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie=(Movie)adapterView.getItemAtPosition(i);
                MovieActivity.start(getActivity(), movie);
            }
        });
    }

    public static MoviesFragment newInstance(){
        return new MoviesFragment();
    }
}