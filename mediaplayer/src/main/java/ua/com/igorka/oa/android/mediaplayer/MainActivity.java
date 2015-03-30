package ua.com.igorka.oa.android.mediaplayer;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String CURRENT_POSITION = "current_position";
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private Button stopButton;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_POSITION);
        }

        Log.i(TAG, "ON_CREATE");
        setContentView(R.layout.activity_main);
        mediaPlayer = new MediaPlayer();

        stopButton = (Button) findViewById(R.id.button_stop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                enableButton(playButton);
                disableButton(stopButton);
            }
        });

        playButton = (Button) findViewById(R.id.button_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButton(playButton);
                mediaPlayer.prepareAsync();
                Log.i(TAG, "PrepareAsync");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mediaPlayer.isPlaying()) {
            outState.putInt(CURRENT_POSITION, mediaPlayer.getCurrentPosition());
        }

    }

    private void disableButton(Button button) {
        button.setClickable(false);
        button.setTextColor(Color.parseColor("#44000000"));
    }

    private void enableButton(Button button) {
        button.setClickable(true);
        button.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (currentPosition > 0) {
                    mediaPlayer.seekTo(currentPosition);
                }
                mp.start();
                enableButton(stopButton);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                currentPosition = 0;
                enableButton(playButton);
                disableButton(stopButton);
            }
        });
        enableButton(playButton);
        disableButton(stopButton);
        Log.i(TAG, mediaPlayer.toString());
        String url = "https://psv4.vk.me/c613829/u95178424/audios/33fa92e6b184.mp3?extra=saw3vvkyIj10U9suUrMtGIXnNzJvi_RX8HyXwx_PXKusffG_wJHZbZCnPWmaI4tKqJb0h4AbwP139ThWLmjynIVIpyODdVc,361";
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "ON_START");
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
            Intent intent = new Intent(this, VideoViewActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_media_recorder) {
            Intent intent = new Intent(this, MediaRecorderActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_take_photo_gallery) {
            Intent intent = new Intent(this, TakePhotoGalleryActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_take_photo_camera) {
            Intent intent = new Intent(this, TakePhotoFromCameraActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_cast_to) {
            Intent intent = new Intent(this, CastActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentPosition > 0) {
            playButton.callOnClick();
        }
        Log.i(TAG, "ON_RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "ON_PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "ON_STOP");
        currentPosition = 0;
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ON_DESTROY");
    }
}
