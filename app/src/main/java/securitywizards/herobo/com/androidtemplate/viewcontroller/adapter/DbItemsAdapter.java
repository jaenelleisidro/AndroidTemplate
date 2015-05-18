package securitywizards.herobo.com.androidtemplate.viewcontroller.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.model.dataaccesslayer.greendao.Box;


/**
 * Created by surecase on 19/03/14.
 */
public class DbItemsAdapter extends ArrayAdapter<Box> {

    private LayoutInflater inflater;
    private Context context;

    public DbItemsAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateData(List<Box> boxList) {
        this.clear();
        for (Box aBoxList : boxList) {
            add(aBoxList);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_box, null);
            viewHolder = new ViewHolder();
            viewHolder.root = (LinearLayout) convertView.findViewById(R.id.boxItem);
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tvItemId);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvSize = (TextView) convertView.findViewById(R.id.tvSize);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        editBackground(position, viewHolder);
        fillViewWithData(position, viewHolder);

        return convertView;
    }

    private void editBackground(int position, ViewHolder viewHolder) {
        if (position % 2 == 0) {
            viewHolder.root.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        } else {
            viewHolder.root.setBackgroundColor(Color.parseColor("#FFAAAAAA"));
        }
    }

    private void fillViewWithData(int position, ViewHolder viewHolder) {
        viewHolder.tvId.setText("ID " + getItem(position).getId().toString());
        viewHolder.tvName.setText("Name " + getItem(position).getName());
        viewHolder.tvSize.setText("Size " + getItem(position).getSlots());
        viewHolder.tvDescription.setText("Description " + getItem(position).getDescription());
    }

    static class ViewHolder {
        LinearLayout root;
        TextView tvId;
        TextView tvName;
        TextView tvSize;
        TextView tvDescription;
    }
}
