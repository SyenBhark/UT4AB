package com.syen.tutorial.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.VideoView;

/**
 * Shows the simulation video to get the answer, typing in 'Android'.
 *
 * @author Syen
 */
public class F04CursorPointerVideoActivity extends AppCompatActivity {

    /**
     * Plays the simulation video with removing its action bar and title bar.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f04_cursor_pointer_video);

        // Makes it full screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // Gets the answer video and plays it.
        VideoView video = (VideoView) findViewById(R.id.videoView_cursor);
        video.setVideoURI(Uri.parse(getString(R.string.uri_prefix) + R.raw.tutorial));
        video.requestFocus();
        video.start();

        // After watching it, finishes the activity.
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // close this activity
                finish();
            }
        }, R00ConstantTimeValues.COMMON_VIDEO_TIME_OUT);
    }
}
