package com.syen.tutorial.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Teaches how to manipulate the cursor pointer by explaining that and showing the video
 * and allowing you to simulate that.
 *
 * @author Syen
 */

public class F04HowToManipulateTheCursorActivity extends AppCompatActivity {

    private Toast toast;
    private Button button;
    private Vibrator vibrator;
    private TextView textView;
    private EditText editText;
    private ImageView imageView;
    private boolean isPassedDoneKey;

    /**
     * Initializes every stuff and set EditorActionListener on the EditText
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f04_how_to_manipulate_the_cursor);

        isPassedDoneKey = false;
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        /**
         * Confirms your answer when the done key pressed.
         */
        editText = (EditText) findViewById(R.id.editText_To_Simulate);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                // When you press the done key,
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Vibrates when you press the done key.
                    vibrator.vibrate(R00ConstantTimeValues.VIBRATE_NORMAL);

                    // Before typing in 'Android'
                    if (!isPassedDoneKey) {
                        // If you type in 'Android', go to the next step that make you to manipulate the cursor pointer.
                        if (editText.getText().toString().contentEquals(getString(R.string.F04HowToManipulateTheCursor_string_answer01))) {
                            //Hides the soft keyboard
                            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                            toast = Toast.makeText(getApplicationContext(), getString(R.string.F04HowToManipulateTheCursor_toast_right01), Toast.LENGTH_SHORT);
                            toast.show();

                            // Shows the next step to manipulating cusor pointer issue.
                            textView = (TextView) findViewById(R.id.textView_HowToManipulateTheCursorSubtitle);
                            textView.setText(getString(R.string.F04HowToManipulateTheCursor_textView_subtitle02));

                            imageView = (ImageView) findViewById(R.id.imageView_cursor_hint);
                            imageView.setBackgroundResource(R.drawable.cursor_pointer);

                            textView = (TextView) findViewById(R.id.textView_cursor_description);
                            textView.setText(getString(R.string.F04HowToManipulateTheCursor_textView_contents02));

                            textView = (TextView) findViewById(R.id.textView_cursor_hint);
                            textView.setText(getString(R.string.F04HowToManipulateTheCursor_textView_hint_contents02));

                            button = (Button) findViewById(R.id.button_to_see_cursor_video);
                            button.setVisibility(View.VISIBLE);

                            // Flags you pressed the done key.
                            isPassedDoneKey = true;
                        } else {    // When you type in a wrong word, alerts that.

                            toast = Toast.makeText(getApplicationContext(), getString(R.string.F04HowToManipulateTheCursor_toast_wrong01), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {  // After typing in 'Android' correctly, you are to prefix 'My'
                        // Checks the input string prefixed correctly.
                        if (editText.getText().toString().contentEquals(getString(R.string.F04HowToManipulateTheCursor_string_answer02))) {
                            //Hides the soft keyboard
                            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                            toast = Toast.makeText(getApplicationContext(), getString(R.string.F04HowToManipulateTheCursor_toast_right02), Toast.LENGTH_SHORT);
                            toast.show();

                            toast = Toast.makeText(getApplicationContext(), getString(R.string.F04HowToManipulateTheCursor_toast_complete), Toast.LENGTH_SHORT);
                            toast.show();

                            new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

                                @Override
                                public void run() {

                                    // close this activity
                                    finish();
                                }
                            }, R00ConstantTimeValues.TOAST_SHORT_TIME);

                        } else {    // When you type in wrong text, alerts that.
                            toast = Toast.makeText(getApplicationContext(), getString(R.string.F04HowToManipulateTheCursor_toast_wrong02), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        /**
         * Makes the Delete key useless when you are to prefix 'My' by manipulating the cursor pointer
         * and alerts that.
         * However, it somtimes doesn't works on API 23. Others are fine.
         */
        editText.setOnKeyListener(new TextView.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (isPassedDoneKey) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_DEL) {
                            toast = Toast.makeText(getApplicationContext(), getString(R.string.F04HowToManipulateTheCursor_toast_alertDoNotUseDel), Toast.LENGTH_SHORT);
                            toast.show();

                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }

    /**
     * Shows the answer video when you click the button.
     *
     * @param view of a clicked object(button).
     */
    public void watchTheSimulationOfManipulatingOnClick(View view) {
        // Vibrates when you click the button.
        vibrator.vibrate(R00ConstantTimeValues.VIBRATE_NORMAL);

        // Shows the video.
        startActivity(new Intent(F04HowToManipulateTheCursorActivity.this, F04CursorPointerVideoActivity.class));
    }
}
