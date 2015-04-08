package ua.com.igorka.oa.android.fragmentation;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 15) {
            int countCodec = MediaCodecList.getCodecCount();
            Log.i(TAG, String.valueOf(Build.VERSION.SDK_INT));

            List<MediaCodecInfo> codecInfoList = new ArrayList<>();

            for (int i = 0; i < countCodec; i++) {
                codecInfoList.add(MediaCodecList.getCodecInfoAt(i));
                Log.i(TAG, MediaCodecList.getCodecInfoAt(i).getName() + " " + MediaCodecList.getCodecInfoAt(i).isEncoder());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
