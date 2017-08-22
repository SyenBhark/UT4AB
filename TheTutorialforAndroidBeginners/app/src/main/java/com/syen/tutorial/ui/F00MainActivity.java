package com.syen.tutorial.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Enumerates functions which are top 5 difficulties that the novices used to be confused.
 *
 * @author Syen
 */

public class F00MainActivity extends AppCompatActivity {

    /**
     * Initializes the Activity
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f00_main);
    }

    /**
     * Goes to the activity corresponding with clicked a button.
     *
     * @param view of a clicked object(button).
     */
    public void switchActivityOnClick(View view) {
        // gets Id of clicked view.
        int viewId = view.getId();

        // Vibrates when you click the button.
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(R00ConstantTimeValues.VIBRATE_NORMAL);

        // The target activity.
        Class goToActivity = null;

        // Puts the target activity into goToActivity.
        if (viewId == R.id.button_goTo_HowToUnlockTheScreenActivity) {
            // 01. How to unlock the screen?
            goToActivity = F01HowToUnlockTheScreenActivity.class;
        } else if (viewId == R.id.button_goTo_HowToAnswerTheIncomingCall) {
            // 02. How to take the incoming call?
            goToActivity = F02HowToAnswerTheIncomingCallActivity.class;
        } else if (viewId == R.id.button_goTo_HowToRunAnAppActivity) {
            // 03. How to run an application?
            goToActivity = F03HowToRunAnAppActivity.class;
        } else if (viewId == R.id.button_goTo_HowToManipulateTheCursorActivity) {
            // 04. How to manipulate the cursor(pointer)?
            goToActivity = F04HowToManipulateTheCursorActivity.class;
        } else if (viewId == R.id.button_goTo_HowToScrollTheScreenActivity) {
            // 05. Turn of WiFi or (Mobile) Data?
            goToActivity = F05HowToScrollTheScreenActivity.class;
        }

        // Starts the target activity.
        if (goToActivity != null) {
            startActivity(new Intent(F00MainActivity.this, goToActivity));
        }
    }
}
