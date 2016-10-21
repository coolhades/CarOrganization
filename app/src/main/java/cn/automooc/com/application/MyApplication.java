package cn.automooc.com.application;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bokecc.sdk.mobile.drm.DRMServer;
import com.hades.libam.net.HttpClientManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public class MyApplication extends Application {

    private static RequestQueue rq;
    static int screenHight;
    static int screenWidth;
    private DRMServer drmServer;

    //记录搜索历史
    private static List<String> historyLists;

    private int drmServerPort;

    public int getDrmServerPort() {
        return drmServerPort;
    }

    public void setDrmServerPort(int drmServerPort) {
        this.drmServerPort = drmServerPort;
    }

    public DRMServer getDRMServer() {
        return drmServer;
    }

    @Override
    public void onTerminate() {
        if (drmServer != null) {
            drmServer.stop();
        }
        super.onTerminate();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //cc加密
        // 启动DRMServer
        drmServer = new DRMServer();
        drmServer.start();
        setDrmServerPort(drmServer.getPort());


        JPushInterface.init(this);
        
        //测试推送
        JPushInterface.setAlias(getApplicationContext(), "D4F0A35F9FE8DECE5F2D0911C938BE4B", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });

        //Volley 请求队列的初始化

        rq = Volley.newRequestQueue(getApplicationContext());

        historyLists=new ArrayList<String>();
        ShareSDK.initSDK(getApplicationContext(), "15e5b1ccdf764");
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHight = wm.getDefaultDisplay().getHeight();

        //初始化client 设置基准域名 才可正常使用网络请求
        HttpClientManager.getInstance().initClient();

    }


    public static int getScreenHight() {
        return screenHight;
    }

    public static void setScreenHight(int screenHight) {
        MyApplication.screenHight = screenHight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        MyApplication.screenWidth = screenWidth;
    }


    public static RequestQueue getRq() {
        return rq;
    }

    public static List<String> getHistoryLists() {
        return historyLists;
    }

    public static void setHistoryLists(List<String> historyLists) {
        MyApplication.historyLists = historyLists;
    }
}
