package securitywizards.herobo.com.androidtemplate.viewcontroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import securitywizards.herobo.com.androidtemplate.R;

/**
* Created by jaenelleisidro on 5/16/15.
*/
public class SimpleListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public SimpleListAdapter(Context context, String[] values) {
        super(context, R.layout.adapter_simplelist_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView=null;
        ViewHolder viewHolder=null;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.adapter_simplelist_row, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.textView = (TextView) rowView.findViewById(R.id.label);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(viewHolder);

        }else{
            rowView=convertView;
            viewHolder=(ViewHolder)rowView.getTag();
        }
        viewHolder.textView.setText(values[position]);
        // change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("iPhone")) {
            viewHolder.imageView.setImageResource(R.drawable.ic_drawer);
        } else {
            viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
        }
        return rowView;
    }

    private class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}


