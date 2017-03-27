package tommy.tappaz.channelmessaging;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class MessageAdapter extends ArrayAdapter<Message> implements OnDownloadCompleteListener {

    private final Context context;
    private final ArrayList<Message> values;
    private View rowView;
    private String url;

    public MessageAdapter(Context context, ArrayList<Message> values) {
        super(context, android.R.layout.simple_list_item_1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(tommy.tappaz.channelmessaging.R.layout.activity_row_message, parent, false);

        TextView txtMsg = (TextView) rowView.findViewById(tommy.tappaz.channelmessaging.R.id.txtMessage);
        txtMsg.setText(values.get(position).message);

        TextView txtUser = (TextView) rowView.findViewById(tommy.tappaz.channelmessaging.R.id.txtUser);
        txtUser.setText(values.get(position).username + " : ");

        TextView txtDate = (TextView) rowView.findViewById(tommy.tappaz.channelmessaging.R.id.txtDate);
        txtDate.setText(values.get(position).date);

        url = values.get(position).imageUrl;
        DownloaderImage d = new DownloaderImage(this, url , "");
        d.setDownloaderList(this);
        d.execute();

        return rowView;

    }

    @Override
    public void onDownloadCompleted(String content, int type) {

        File imgFile = new File(url);
        if(imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView imgUser = (ImageView) rowView.findViewById(tommy.tappaz.channelmessaging.R.id.imgUser);
            imgUser.setImageBitmap(myBitmap);
        }

    }
}
