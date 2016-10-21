package com.hades.quicklibrary.video;

import android.media.MediaPlayer;

/**
 * Created by Hades on 16/10/11.
 */

public interface PlayerStatusListener {
     void onPrepared(MediaPlayer mediaPlayer);
    void onCompletion(MediaPlayer mediaPlayer);
    void onSeekComplete(MediaPlayer m);
    boolean onError(MediaPlayer mediaPlayer, int i, int i1);
    void onBufferingUpdate(MediaPlayer mp, int percent);
    boolean onInfo(MediaPlayer mp, int what, int extra);
}
