package ua.com.igorka.oa.android.gson;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import ua.com.igorka.oa.android.gson.data.Store;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String data = "{\"name\":\"FruitStore\",\"product_categories\":[{\"name\":\"Category1\",\"products\":[{\"name\":\"Apple\",\"amount\":\"10\"},{\"name\":\"Pear\",\"amount\":\"5\"}]},{\"name\":\"Category2\",\"products\":[{\"name\":\"Apple2\",\"amount\":\"8\"},{\"name\":\"Pear2\",\"amount\":\"25\"}]}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Store store = new Store();

        Gson gson = new Gson();
        store = gson.fromJson(data, Store.class);

        Log.i(TAG, store.toString());
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
