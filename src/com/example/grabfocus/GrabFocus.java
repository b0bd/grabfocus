package com.example.grabfocus;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.os.Process;
import android.util.Log;

public class GrabFocus extends Activity
{
    private static final String TAG = "GrabFocus";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");

        setContentView(R.layout.main);

        final TextView focusInfoTV = (TextView) findViewById(R.id.focusInfo);
        focusInfoTV.setText("Trying to gain audio focus");

        final AudioFocus af = new AudioFocus(this);
        af.grabFocus();

        final Handler h = new Handler();
        h.postDelayed(new Runnable()
            {
                public void run()
                {
			        Log.i(TAG,"onCreate postDelayed finish()");
			        af.releaseFocus();
                    finish();
                }
            },
            3000);

        Log.i(TAG,"onCreate done");
    }



    @Override
    protected void onDestroy()
    {
        Log.i(TAG,"onDestroy");
        //Process.killProcess(Process.myPid());
        super.onDestroy();
        Log.i(TAG,"onDestroy done");
    }


    private final AudioManager.OnAudioFocusChangeListener _changeListener =
        new AudioManager.OnAudioFocusChangeListener()
        {
            public void onAudioFocusChange(int focusChange)
            {
                // do nothing;
		        Log.i(TAG,"onAudioFocusChange focusChange="+focusChange+" do nothing");
            }
        };
}

