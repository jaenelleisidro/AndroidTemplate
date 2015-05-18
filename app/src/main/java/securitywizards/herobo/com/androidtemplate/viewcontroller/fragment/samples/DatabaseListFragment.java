package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.model.businesslayer.BoxRepository;
import securitywizards.herobo.com.androidtemplate.model.dataaccesslayer.greendao.Box;
import securitywizards.herobo.com.androidtemplate.other.helper.AndroidUtils;
import securitywizards.herobo.com.androidtemplate.viewcontroller.adapter.DbItemsAdapter;
import securitywizards.herobo.com.androidtemplate.viewcontroller.adapter.SimpleListAdapter;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;

/**
 * Fragment which houses the View pager.
 */
public class DatabaseListFragment extends BaseFragment {

    @Inject AndroidUtils androidUtils;
    @Inject BoxRepository boxRepository;

    @InjectView(R.id.lvListHolder)
    ListView lvListHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simplelist, container, false);
    }

    @Override
    public void onActivityCreated2(Bundle savedInstanceState) {
        DbItemsAdapter adapter=new DbItemsAdapter(getActivity());
        lvListHolder.setAdapter(adapter);
        lvListHolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                androidUtils.alert("Click ListItem Number " + position);
            }
        });
        List<Box> boxes=boxRepository.getAllBoxes(getActivity());
        if(boxes.size()==0){
            for(int ctr=1;ctr<=100;ctr++) {
                Box box = new Box();
                box.setDescription("Description");
                box.setName("Name="+ctr);
                box.setSlots(ctr);
                boxRepository.insertOrUpdate(getActivity(), box);
            }
            boxes=boxRepository.getAllBoxes(getActivity());
        }

        adapter.updateData(boxes);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){}
    @Override
    public void onSaveInstanceState2(Bundle outState) {}

    public static Fragment newInstance(){
        DatabaseListFragment simpleFragment =new DatabaseListFragment();
        return simpleFragment;
    }
}