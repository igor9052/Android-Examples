package ua.com.igorka.oa.android.examples;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements CalcKeyboardFragment1.OnCalcButtonPressedListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    TextView calcScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, AppCycle.ON_CREATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcScreen = (TextView) findViewById(R.id.calc_screen);
        Fragment keyboardFragment = new CalcKeyboardFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_keyboard, new CalcKeyboardFragment())
                .commit();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, AppCycle.ON_START);
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, AppCycle.ON_RESUME);
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        Log.d(TAG, AppCycle.ON_POST_RESUME);
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, AppCycle.ON_PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, AppCycle.ON_STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, AppCycle.ON_DESTROY);
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, AppCycle.ON_RESTORE_INSTANCE_STATE);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, AppCycle.ON_SAVE_INSTANCE_STATE);
        super.onSaveInstanceState(savedInstanceState);
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
        if (id == R.id.action_show_keyboard) {
            //showKeyboard();
            showKeyboard(true, new CalcKeyboardFragment1());
            return true;
        }
        if (id == R.id.action_show_keyboard_backstack) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_keyboard, new CalcKeyboardFragment1())
                    .addToBackStack(null)
                    .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showKeyboard(Fragment fragment) {
        showKeyboard(false, fragment);
    }


    private void showKeyboard(boolean isBackStack, Fragment fragment) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCalcButtonPressed(String keyValue) {
        calcScreen.setText(calcScreen.getText().toString() + keyValue);
    }

    private static final class AppCycle {
        public static final String ON_CREATE = "ON_CREATE";
        public static final String ON_START = "ON_START";
        public static final String ON_RESUME = "ON_RESUME";
        public static final String ON_POST_RESUME = "ON_POST_RESUME";
        public static final String ON_PAUSE = "ON_PAUSE";
        public static final String ON_STOP = "ON_STOP";
        public static final String ON_DESTROY = "ON_DESTROY";
        public static final String ON_SAVE_INSTANCE_STATE = "ON_SAVE_INSTANCE_STATE";
        public static final String ON_RESTORE_INSTANCE_STATE = "ON_RESTORE_INSTANCE_STATE";

    }
}


