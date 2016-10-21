package com.hades.quicklibrary.video;

import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by Hades on 16/10/11.
 * 依赖SurfaceviewManager
 * 设置Holder初始化监听 Created后自动初始化Player 绑定Holder
 */

public class VideoPlayer {

    MediaPlayer player;
    PlayerStatusListener playerlistener;


    public VideoPlayer() {
        //设置监听接口 绑定surfaceholder
        SurfaceViewManager.getInstance().setOnInitPlayer(new IInitPlayer() {
            @Override
            public void InitPlayer() {
                initPlayer();
            }
        });
    }

    public void setOnPlayerStatusListener(PlayerStatusListener l){
        playerlistener = l;
    }

    private void initPlayer() {
        if (playerlistener == null){
            return;
        }
        if (player != null) {
            player.release();
        }
        player = new MediaPlayer();
        player.reset();
        player.setDisplay(SurfaceViewManager.getInstance().getHolder() );//绑定holder
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setScreenOnWhilePlaying(true);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //暴露回调
                playerlistener.onPrepared(mediaPlayer);
            }
        });

        //播放完成
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playerlistener.onCompletion(mediaPlayer);
            }
        });

        player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            public void onSeekComplete(MediaPlayer m) {
                playerlistener.onSeekComplete(m);
            }
        });

        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                return playerlistener.onError(mediaPlayer, i, i1);
            }
        });
        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playerlistener.onBufferingUpdate(mp, percent);
            }
        });

        player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return playerlistener.onInfo(mp, what, extra);
//                switch (what) {
//                    case DWMediaPlayer.MEDIA_INFO_BUFFERING_START:
//                        if (player.isPlaying()) {
//                            bufferProgressBar.setVisibility(View.VISIBLE);
//                        }
//                        break;
//                    case DWMediaPlayer.MEDIA_INFO_BUFFERING_END:
//                        bufferProgressBar.setVisibility(View.GONE);
//                        break;
//                }
//                return false;
            }
        });
    }

    public void setVideoData(){

    }

    public void startPlay(){
            if (player.isPlaying()) {
                player.pause();
            } else {
            player.start();
        }

    }
}
