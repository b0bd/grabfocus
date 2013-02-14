package com.example.grabfocus;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

final class AudioFocus implements AudioManager.OnAudioFocusChangeListener
{
    private static final String TAG = "GrabFocus";
    private final AudioManager am;

    public AudioFocus(Context ctx) {
        am = (AudioManager)ctx.getSystemService(Context.AUDIO_SERVICE);
    }

    public void grabFocus() {
        //Log.i(TAG,"AudioFocus grabFocus");
        final int result = am.requestAudioFocus(this,
                                                AudioManager.STREAM_MUSIC,
                                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT); //AudioManager.AUDIOFOCUS_GAIN);
        Log.i(TAG,"AudioFocus grabFocus - tried to grab focus result="+
            ((result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)?"granted":"failed"));
    }

    public void releaseFocus() {
        //Log.i(TAG,"AudioFocus releaseFocus");
        final int result = am.abandonAudioFocus(this);
        Log.i(TAG,"AudioFocus releaseFocus - tried to abandon focus result="+
            ((result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)?"granted":"failed"));
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch(focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                Log.i(TAG,"AudioFocus onAudioFocusChange AUDIOFOCUS_GAIN");
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                Log.i(TAG,"AudioFocus onAudioFocusChange AUDIOFOCUS_LOSS");
                break;
            default:
                Log.i(TAG,"AudioFocus onAudioFocusChange focusChange="+focusChange);
        }
    }
}
