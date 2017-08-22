package com.syen.tutorial.ui;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.VideoView;


/**
 * Shows the simulation video which is one of common ways to answer the incoming call.
 *
 * @author Syen
 */
public class F02AnswerTheCallVideoActivity extends AppCompatActivity {
    Ringtone ringtone;
    VideoView video;

    /**
     * Plays the simulation video with removing its action bar and title bar.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f02_answer_the_call_video);

        // Makes it full screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        // Gets unlock video and plays it.
        // cite: http://pngimg.com/download/946 image
        video = (VideoView) findViewById(R.id.videoView_incoming_call);
        video.setVideoURI(Uri.parse(getString(R.string.uri_prefix) + R.raw.incoming_call));
        video.requestFocus();
        video.start();

        // Plays user's default ringtone when the phone rings in the simulation video.
        // cite: http://stackoverflow.com/questions/2618182/how-to-play-ringtone-alarm-sound-in-android
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        // Starts playing the ringtone after starting the video few minutes.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ringtone.play();
            }
        }, R00ConstantTimeValues.RINGTONE_START_TIME);

        // Stops playing the ringtone before stopping the video.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ringtone.stop();
            }
        }, R00ConstantTimeValues.RINGTONE_STOP_TIME);


        // After watching it, finishes the activity.
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // close this activity
                finish();
            }
        }, R00ConstantTimeValues.INCOMING_CALL_VIDEO_TIME_OUT);
    }

    /**
     *  Stops playing the ringtone and destroys the activity when you cannot see the video.
     *  For example, when you press the home key or hold key or back key.
     *  This is for trying to synchronize playing the video with user's ringtone.
     */
    @Override
    public void onStop() {

        super.onStop();
        ringtone.stop();
        finish();
    }

}
