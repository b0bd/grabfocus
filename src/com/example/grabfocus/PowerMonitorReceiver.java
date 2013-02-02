package com.example.grabfocus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public final class PowerMonitorReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.i("bobpmr", "got intent = " +intent.getAction());

        if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction()))
        {
            final AudioFocus af = new AudioFocus(context);
            af.grabFocus();
            af.releaseFocus();
        }
    }
}
