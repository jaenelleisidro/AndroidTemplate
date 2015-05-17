package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;

import butterknife.InjectView;
import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;

/**
 * Fragment which houses the View pager.
 */
public class SwipeFragment extends BaseFragment {
    @InjectView(R.id.bottom_wrapper)
    View bottomWrapper;
    @InjectView(R.id.swipeLayout)
    SwipeLayout swipeLayout;

    @Override
    public View onCreateView2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_swipe, container, false);
    }

    @Override
    public void onActivityCreated2(Bundle savedInstanceState) {
//set show mode.
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

//add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, bottomWrapper);

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });
        swipeLayout.setClickToClose(true);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
    }

    //Here you Save your data
    @Override
    public void onSaveInstanceState2(Bundle outState) {
    }



    public static Fragment newInstance(){
        SwipeFragment simpleFragment =new SwipeFragment();
        return simpleFragment;
    }
}