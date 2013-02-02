package com.example.grabfocus;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

final class AudioFocus
{
    AudioFocus(Context ctx)
    {
        _ctx = ctx;
    }


    public void grabFocus()
    {
        final AudioManager am = (AudioManager) _ctx.getSystemService(Context.AUDIO_SERVICE);
        final int result = am.requestAudioFocus(_changeListener,
                                                AudioManager.STREAM_MUSIC,
                                                AudioManager.AUDIOFOCUS_GAIN);
        Log.d("AudioFocus","tried to grab focus result " + result);
    }


    public void releaseFocus()
    {
        final AudioManager am = (AudioManager) _ctx.getSystemService(Context.AUDIO_SERVICE);
        final int result = am.abandonAudioFocus(_changeListener);
        Log.d("AudioFocus","tried to abandon focus result " + result);
    }


    private final AudioManager.OnAudioFocusChangeListener _changeListener =
        new AudioManager.OnAudioFocusChangeListener()
        {
            public void onAudioFocusChange(int focusChange)
            {
                //nothing to do
            }
        };


    private final Context _ctx;
}
