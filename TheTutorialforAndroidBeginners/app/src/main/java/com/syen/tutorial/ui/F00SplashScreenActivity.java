package com.syen.tutorial.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Builds a splash screen.
 * Cite: http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
 *
 * @author Syen
 */
public class F00SplashScreenActivity extends Activity {

    /**
     * Shows the splash screen for a moment.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f00_splash_screen);

        //Shows a splash screen with a timer.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(F00SplashScreenActivity.this, F00MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, R00ConstantTimeValues.SPLASH_TIME_OUT);
    }
}