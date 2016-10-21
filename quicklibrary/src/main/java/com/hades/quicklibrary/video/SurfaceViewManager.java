package com.hades.quicklibrary.video;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Hades on 16/10/11.
 * 1、需要注入 surfaceView
 * 2、InitHolder
 * 3、创建PlayerManager对象
 */

public class SurfaceViewManager {
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    IInitPlayer iInitPlayer;
    private boolean isSurfaceCreated = false;


    private SurfaceViewManager() {
    }

    public static SurfaceViewManager getInstance(){
        return SingtonInstance.instance;
    }

    private static class SingtonInstance{
        public static SurfaceViewManager instance = new SurfaceViewManager();
    }



    public void setOnInitPlayer(IInitPlayer onInitPlayer){
        iInitPlayer = onInitPlayer;
    }


    public void setSurfaceView(SurfaceView view){
        if (view!= null){
            surfaceView = view;
        }
    }

    public SurfaceHolder getHolder(){
        if (surfaceView != null) {
            surfaceHolder = surfaceView.getHolder();
            return surfaceHolder;
        }else return null;
    }

    public void InitHolder(){


        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                isSurfaceCreated = true;
                iInitPlayer.InitPlayer();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        // surfaceHolder.setFixedSize(320, 220);//显示的分辨率,不设置为视频默认
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//Surface类型

    }
}
