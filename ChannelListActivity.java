package tommy.tappaz.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.HashMap;

public class ChannelListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnDownloadCompleteListener {

    private ListView lVChannels;
    private String accesstoken;
    private Channels chs;

    public static final String PREFS_NAME = "Stockage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(tommy.tappaz.channelmessaging. R.layout.activity_channel_list);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        accesstoken = settings.getString("accesstoken", "");

        HashMap<String, String> Params = new HashMap<String, String>();
        Params.put("accesstoken", accesstoken);

        Downloader d = new Downloader(this, "http://www.raphaelbischof.fr/messaging/?function=getchannels" , Params);
        d.setDownloaderList(this);
        d.execute();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Channel myChannel = chs.channels.get(position);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("channelID", String.valueOf(myChannel.channelID));
        editor.commit();

        Intent newActivity = new Intent(getApplicationContext(),ChannelMessage.class);
        //newActivity.pu
        startActivity(newActivity);

    }

    @Override
    public void onDownloadCompleted(String result, int type) {

        Gson gson = new Gson();
        chs = gson.fromJson(result, Channels.class);


        lVChannels = (ListView) findViewById(tommy.tappaz.channelmessaging.R.id.lVChannels);
        lVChannels.setAdapter(new ChannelsAdapter(getApplicationContext(), chs.channels));
        lVChannels.setOnItemClickListener(this);

    }
}
