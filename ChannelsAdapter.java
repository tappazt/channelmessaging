package tommy.tappaz.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChannelsAdapter extends ArrayAdapter<Channel> {

    private final Context context;
    private final ArrayList<Channel> values;

    public ChannelsAdapter(Context context, ArrayList<Channel> values) {
        super(context, android.R.layout.simple_list_item_1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(tommy.tappaz.channelmessaging.R.layout.activity_row_channel, parent, false);
        TextView textView = (TextView) rowView.findViewById(tommy.tappaz.channelmessaging.R.id.titleChannel);
        TextView txtNbUser = (TextView) rowView.findViewById(tommy.tappaz.channelmessaging.R.id.nbUser);
        textView.setText(values.get(position).name);
        txtNbUser.setText("Nombre d'utilisateurs connectés : " + values.get(position).connectedusers);
        return rowView;
    }

}
