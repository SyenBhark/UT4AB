package com.syen.tutorial.ui;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Teaches how to run an application by explaining that and allowing you to simulate that.
 *
 * @author Syen
 */
public class F03HowToRunAnAppActivity extends AppCompatActivity {
    private boolean didYouRunTheApp;
    private Button button_lanchApp;
    private TextView textView_hiddenTextViews;
    private ScrollView scrollView;
    private Toast toast;
    private Vibrator vibrator;

    /**
     * Initializes every stuff and set onClickListener and onLongClickListener on the app button
     * and onDragListener on the ScrollView and the dropBox TextView.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f03_how_to_run_an_app);

        didYouRunTheApp = false;

        scrollView = (ScrollView) findViewById(R.id.scrollView_how_to_run_an_App);

        button_lanchApp = (Button) findViewById(R.id.button_app);
        button_lanchApp.setTag(getString(R.string.F03HowToRunAnApp_button_theApp));

        textView_hiddenTextViews = (TextView) findViewById(R.id.textView_drop_here);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        /**
         * Allows you to simulate running an application by just clicking.
         */
        button_lanchApp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vibrates when you click the button for short time.
                vibrator.vibrate(R00ConstantTimeValues.VIBRATE_SHORT);

                // If you do not succeed to run the app yet, clicking button allows you to run the app.
                // Shows the next instructions.
                if (!didYouRunTheApp) {
                    toast = Toast.makeText(getApplicationContext(), getString(R.string.F03HowToRunAnApp_toast_click_right01), Toast.LENGTH_SHORT);
                    toast.show();

                    textView_hiddenTextViews = (TextView) findViewById(R.id.textView_what_the_diff_is);
                    textView_hiddenTextViews.setVisibility(View.VISIBLE);

                    textView_hiddenTextViews = (TextView) findViewById(R.id.textView_desc_of_what_the_diff);
                    textView_hiddenTextViews.setVisibility(View.VISIBLE);

                    textView_hiddenTextViews = (TextView) findViewById(R.id.textView_drop_here);
                    textView_hiddenTextViews.setVisibility(View.VISIBLE);

                    didYouRunTheApp = true;

                    // Scrolls to the end of view.
                    scrollView.scrollTo(0, (int) button_lanchApp.getY());
                } else {    // If you succeeded to run the app, you are to do long click, so alerts that.
                    toast = Toast.makeText(getApplicationContext(), getString(R.string.F03HowToRunAnApp_toast_click_wrong01), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        /**
         * Allows you to simulate moving application by just doing long click.
         */
        button_lanchApp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Vibrates when you click the button for long time.
                vibrator.vibrate(R00ConstantTimeValues.VIBRATE_LONG);

                // If you do not succeed to run the app yet, you are click, so alerts that.
                if (!didYouRunTheApp) {
                    toast = Toast.makeText(getApplicationContext(), getString(R.string.F03HowToRunAnApp_toast_longClick_wrong01), Toast.LENGTH_SHORT);
                    toast.show();
                } else {    // If you succeeded to run the app, doing long click button allows you to move the app.
                    toast = Toast.makeText(getApplicationContext(), getString(R.string.F03HowToRunAnApp_toast_longClick_right01), Toast.LENGTH_SHORT);
                    toast.show();
                    ClipData dragData = ClipData.newPlainText(view.getTag().toString(), view.getTag().toString());
                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(view);
                    view.startDrag(dragData, myShadow, view, 0);
                }
                return true;
            }
        });

        /**
         * Scrolls the view down and alerts message when you miss the app.
         */
        scrollView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent event) {
                // Defines a variable to store the action type for the incoming event
                final int action = event.getAction();

                switch (action) {
                    case DragEvent.ACTION_DRAG_LOCATION:
                        view.scrollBy(0, (int) event.getY() / 2);
                        break;

                    case DragEvent.ACTION_DROP:
                        toast = Toast.makeText(getApplicationContext(), getString(R.string.F03HowToRunAnApp_toast_longClick_wrong02), Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                }

                return true;
            }
        });

        /**
         * Detects that you drop the app on the drop box and finishes the activity.
         */
        textView_hiddenTextViews.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent event) {
                // Defines a variable to store the action type for the incoming event
                final int action = event.getAction();

                // Handles each of the expected events
                switch (action) {
                    case DragEvent.ACTION_DROP:
                        button_lanchApp.setX(view.getX() + (view.getWidth() - button_lanchApp.getWidth()) / 2);
                        button_lanchApp.setY(view.getY() - (button_lanchApp.getHeight() - view.getHeight()) / 2);

                        // Lets you know that you complete the mission.
                        toast = Toast.makeText(getApplicationContext(), getString(R.string.F03HowToRunAnApp_toast_complete), Toast.LENGTH_SHORT);
                        toast.show();

                        // Destroys the activity.
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // close this activity
                                finish();
                            }
                        }, R00ConstantTimeValues.TOAST_SHORT_TIME);
                        break;
                }
                return true;
            }
        });
    }
}