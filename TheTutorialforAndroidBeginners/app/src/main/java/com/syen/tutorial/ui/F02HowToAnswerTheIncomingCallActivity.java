package com.syen.tutorial.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Teaches how to answer the incoming call by showing the simulation video and explaining it.
 *
 * @author Syen
 */

public class F02HowToAnswerTheIncomingCallActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private Vibrator vibrator;

    /**
     * Initializes the flag boolean that checks whether you watch the video, or not.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f02_how_to_answer_the_incoming_call);

        checkBox = (CheckBox) findViewById(R.id.checkBox_done);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * Shows the simulation video when you click a button.
     *
     * @param view of an object(Button).
     */
    public void setOnClick_watchTheSimulationOfIncomingCall(View view) {
        // Vibrates when you click the button.
        vibrator.vibrate(R00ConstantTimeValues.VIBRATE_NORMAL);

        // starts the activity that plays the simulation video.
        startActivity(new Intent(F02HowToAnswerTheIncomingCallActivity.this, F02AnswerTheCallVideoActivity.class));
    }

    /**
     * Destroys the activity when you click the button.
     *
     * @param view of an object(Button).
     */
    public void setOnClick_backToMain(View view) {
        // Vibrates when you click the button.
        vibrator.vibrate(R00ConstantTimeValues.VIBRATE_NORMAL);

        // When you check the check box, it means you did.
        if (checkBox.isChecked()) {
            Toast.makeText(getApplicationContext(), getString(R.string.F02HowToAnswerTheIncomingCallActivity_toast_complete), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.F02HowToAnswerTheIncomingCallActivity_toast_non_complete), Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
