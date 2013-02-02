package com.example.grabfocus;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.os.Process;


public class GrabFocus extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final TextView focusInfoTV = (TextView) findViewById(R.id.focusInfo);
        focusInfoTV.setText("Trying to gain audio focus");

        new AudioFocus(this).grabFocus();

        final Handler h = new Handler();
        h.postDelayed(new Runnable()
            {
                public void run()
                {
                   finish();
                }
            },
            3000);
    }



    @Override
    protected void onDestroy()
    {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }


    private final AudioManager.OnAudioFocusChangeListener _changeListener =
            new AudioManager.OnAudioFocusChangeListener()
            {
                public void onAudioFocusChange(int focusChange)
                {
                    // do nothing;
                }
            };
}
