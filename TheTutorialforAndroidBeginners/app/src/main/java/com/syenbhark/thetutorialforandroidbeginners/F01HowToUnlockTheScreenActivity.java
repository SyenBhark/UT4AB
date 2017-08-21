package com.syenbhark.thetutorialforandroidbeginners;

import java.util.Locale;

import android.os.Bundle;
import android.view.View;
import android.os.Vibrator;
import android.widget.Toast;
import android.text.Spanned;
import android.graphics.Color;
import android.content.Intent;
import android.widget.TextView;
import android.content.Context;
import android.text.SpannableString;
import android.content.ComponentName;
import android.text.style.RelativeSizeSpan;
import android.text.SpannableStringBuilder;
import android.app.admin.DevicePolicyManager;
import android.text.style.ForegroundColorSpan;
import android.support.v7.app.AppCompatActivity;


/**
 * Teaches how to unlock the screen by showing the simulation video and allow you to try it out.
 * cite: http://stackoverflow.com/questions/19745890/how-do-i-lock-phone-programmatically-android
 *
 * @author Syen
 */

public class F01HowToUnlockTheScreenActivity extends AppCompatActivity {

    private TextView notice;
    private Vibrator vibrator;
    private boolean didUnlockTheScreen;
    private ComponentName mComponentName;
    private boolean isResumedAfterTryingItOut;
    private static final int ADMIN_INTENT = 15;
    private DevicePolicyManager mDevicePolicyManager;

    /**
     * Initializes a device manager, a component to lock screen and a flag boolean variable.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_f01_how_to_unlock_the_screen);

        didUnlockTheScreen = false;
        isResumedAfterTryingItOut = false;
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        notice = (TextView) findViewById(R.id.textView_permission_notice);
        mComponentName = new ComponentName(this, F01MyAdminReceiver.class);
        mDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        notice.setText(sizeUpSubString(getResources().getString(R.string.F01HowToUnlockTheScreenActivity_textView_notice), "#E53935"));
    }

    /**
     * Shows the simulation video when you click a button.
     *
     * @param view of an object(Button).
     */
    public void watchTheSimulationOfUnlockingOnClick(View view) {
        // Vibrates when you click the button.
        vibrator.vibrate(R00ConstantTimeValues.VIBRATE_NORMAL);

        // starts the activity that plays the simulation video.
        startActivity(new Intent(F01HowToUnlockTheScreenActivity.this, F01UnlockTheScreenVideoActivity.class));
    }

    /**
     * Allows you to try 'unlock the basic screen' out by locking your device screen.
     *
     * @param view of an object(Button).
     */
    public void tryUnlockingOutOnClick(View view) {
        // Vibrates when you click the button.
        vibrator.vibrate(R00ConstantTimeValues.VIBRATE_NORMAL);

        // Tries to get the device adim.
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, getString(R.string.description));
        startActivityForResult(intent, ADMIN_INTENT);

        // Checks whether you can administer the device, or not.
        boolean isAdmin = mDevicePolicyManager.isAdminActive(mComponentName);

        // If you are the administer, locks the device screen and you are going to unlock the screen.
        if (isAdmin) {
            didUnlockTheScreen = true;
            mDevicePolicyManager.lockNow();
        } else {    // Otherwise, alert that you are not the administer.
            Toast.makeText(getApplicationContext(), getString(R.string.F01HowToUnlockTheScreenActivity_toast_alertAdmin), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Destroys the activity when it is resumed after doing unlock the screen.
     */
    protected void onResume() {
        super.onResume();

        // You are done this lesson!
        if (isResumedAfterTryingItOut) {
            finish();
            Toast.makeText(getApplicationContext(), getString(R.string.F01HowToUnlockTheScreenActivity_toast_complete), Toast.LENGTH_SHORT).show();
        }

        // After clicking the trying it out button, this activity will be resumed because you did unlock the screen.
        if (didUnlockTheScreen) {
            isResumedAfterTryingItOut = true;
        }
    }

    /**
     * @param string Target string
     * @param color  Color
     * @return a string with a resized substring
     */
    public SpannableStringBuilder sizeUpSubString(String string, String color) {

        int flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        SpannableString s = new SpannableString(string);
        int index = Locale.getDefault().getLanguage().compareTo("ko") == 0 ? 9 : 35;

        s.setSpan(new RelativeSizeSpan(0.7f), index, string.length(), flag);
        s.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 0, string.length(), flag);

        return new SpannableStringBuilder().append(s);
    }

}
