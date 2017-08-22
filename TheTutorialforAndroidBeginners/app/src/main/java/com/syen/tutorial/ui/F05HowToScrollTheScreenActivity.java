package com.syen.tutorial.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Teaches how to scroll the screen by explaining that and allows you try that out.
 *
 * @author Syen
 */

public class F05HowToScrollTheScreenActivity extends AppCompatActivity {
    private EditText editText_scroll;
    private Vibrator vibrator;
    private Toast toast;

    /**
     * Initialize stuff and get the input text from the EditText and checks it.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f05_how_to_scroll_the_screen);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        /**
         * Confirms your answer when the done key pressed.
         */
        editText_scroll = (EditText) findViewById(R.id.editText_scrolling);
        editText_scroll.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                //
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Vibrates when you press the done key.
                    vibrator.vibrate(R00ConstantTimeValues.VIBRATE_NORMAL);

                    //Hides the soft keyboard
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    // When you wrote the right text, finishes the activity.
                    if (editText_scroll.getText().toString().contentEquals(getString(R.string.F05HowToScrollTheScreen_answer))) {
                        toast = Toast.makeText(getApplicationContext(), getString(R.string.F05HowToScrollTheScreen_toast_complete), Toast.LENGTH_SHORT);
                        toast.show();

                        finish();
                    } else {    // When you type in a wrong word, alerts that.
                        toast = Toast.makeText(getApplicationContext(), getString(R.string.F05HowToScrollTheScreen_toast_wrong), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
