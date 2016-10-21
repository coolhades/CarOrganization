package cn.automooc.com.ui;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;

import com.hades.libam.video.PlayerStatusListener;
import com.hades.libam.video.SurfaceViewManager;
import com.hades.libam.video.VideoPlayer;

import cn.automooc.com.R;

public class TestActivity extends AppCompatActivity {

    SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        surfaceView = (SurfaceView) findViewById(R.id.playerSurfaceView);

        //
        SurfaceViewManager.getInstance().setSurfaceView(surfaceView);
        SurfaceViewManager.getInstance().getHolder();
        SurfaceViewManager.getInstance().InitHolder();

        //创建对象 内部设置了监听,一旦surfaceCreated 就会初始化Player 并绑定holder
        final VideoPlayer player = new VideoPlayer();
        //设置视频资源
        player.setVideoData();
        //调用播放

        //player的监听事件
        player.setOnPlayerStatusListener(new PlayerStatusListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //资源准备好 可以播放了
                player.startPlay();

//                videoImg.setVisibility(View.GONE);
//                sharedBar.setVisibility(View.INVISIBLE);
//                player.start();
//                player.seekTo(currentPosition * 1000);
//                isComplete = false;
//                Log.i("TAG-SetPlayer", "player,Prepared");
//                show();
//                flag = true;
//                startBt.setImageResource(R.mipmap.start_img);
//                Message msg = Message.obtain();
//                msg.arg1 = 101;
//                handler.sendMessage(msg);
//                setCurreProgress();
            }

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }

            @Override
            public void onSeekComplete(MediaPlayer m) {

            }

            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                return false;
            }

            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {

            }

            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }


}
