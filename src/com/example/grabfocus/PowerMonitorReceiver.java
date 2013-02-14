package com.example.grabfocus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.SystemProperties;

public final class PowerMonitorReceiver extends BroadcastReceiver {

    private static final String TAG = "GrabFocus";
    private static AudioFocus af = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.i(TAG, "PowerMonitorReceiver got intent="+intent.getAction());

        final String USE_FI_MODE_PROP = "persist.sys.use_fi_mode";
        final String USE_FI_MODE_DEFAULT = "1";
        final String FI_MODE_FILE = "/sys/kernel/usbhost/usbhost_fixed_install_mode";
        String useFiMode = SystemProperties.get(USE_FI_MODE_PROP, USE_FI_MODE_DEFAULT);
        if("0".equals(useFiMode)) {
            Log.i(TAG, "PowerMonitorReceiver intent="+intent.getAction()+" ignored outside FI mode");
        } else {
            Log.i(TAG, "PowerMonitorReceiver intent="+intent.getAction()+" being processed in FI mode...");
            if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
                af = new AudioFocus(context);
                if(af!=null) {
                    af.grabFocus();
                } else {
                    Log.i(TAG, "PowerMonitorReceiver intent="+intent.getAction()+" failed to grab focus");
                }
            } else
            if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
                if(af!=null) {
                    af.releaseFocus();
                    af = null;
                } else {
                    Log.i(TAG, "PowerMonitorReceiver intent="+intent.getAction()+" failed to release focus");
                }
            } else {
                Log.i(TAG, "PowerMonitorReceiver unknown intent="+intent.getAction());
            }
        }
    }
}
