package cn.automooc.com.ui;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bokecc.sdk.mobile.play.DWMediaPlayer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hades.libam.utils.SPUtils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.BaseActivity;
import cn.automooc.com.R;
import cn.automooc.com.adapter.VideoViewPagerAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.ClassResultDetail;
import cn.automooc.com.bean.ResultDetail;
import cn.automooc.com.bean.Sign;
import cn.automooc.com.bean.WatchOnBean;
import cn.automooc.com.bean.ZhangBean;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.LoadImgUtils;
import cn.automooc.com.utils.Md5Utils;
import cn.automooc.com.utils.SharedSdkUtils;
import cn.automooc.com.utils.ShowMaskDialogUtils;
import cn.automooc.com.videointerface.OnVideoDataListener;
import cn.automooc.com.view.VideoView1;
import cn.automooc.com.view.VideoView2;
import cn.automooc.com.view.VideoView3;
import cn.automooc.com.view.VideoView4;
import cn.automooc.com.widget.WebViewDialog;

public class VideoActivity extends BaseActivity implements SurfaceHolder.Callback {

    ViewPager viewPager;
    VideoViewPagerAdapter adapter;
    List<View> lists;

    ImageView back;
    // MediaPlayer player;

    DWMediaPlayer player;

    SurfaceHolder surfaceHolder;
    SurfaceView surface;

    boolean isComplete = false;

    ImageView startBt;
    SeekBar progressBar;
    ImageView expendBt;
    ImageView back_fullscreem;
    TextView videoName;
    TextView time1;//播放位置时间
    TextView time2;//总时间

    boolean isFullScreen = false;

    RelativeLayout playerLayout;
    LinearLayout topBar;


    TextView jianjieBt;
    TextView muluBt;
    TextView tiwenBt;
    TextView xuexiBt;
    TextView videoTitle;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;

    ImageView sharedImg;
    ImageView collectImg;

    boolean isFollow = false;



    LinearLayout videoBar;
    ResultDetail resultLessonKc;

    ImageView videoImg;

    LinearLayout sharedBar;
    ImageView wx_bt;
    ImageView wx_friend_bt;
    ImageView sina_bt;

    ImageView replayer;
    ImageView playNext;

    ImageView guide_iv;

    ProgressBar bufferProgressBar;

    TextView btnGetCode;


    //    int intRxamPosition=10000000;
    int exam_count;
    int currentPosition = 0;
    boolean flag = false;
    private TimeCount time = new TimeCount(5000, 1000);//5s后自动播放


    VideoView1 videoView1;
    VideoView4 videoView4;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progressBar == null) {
                return;
            }
            if (player == null) {
                return;
            }

            if (msg.arg1 == 100) {
                if (flag) {
                    progressBar.setProgress(player.getCurrentPosition());
                }
            } else if (msg.arg1 == 101) {
                if (player.isPlaying()) {
                    progressBar.setMax(player.getDuration());
                }
            }

            if (flag) {
                //播放中
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                    if (player.isPlaying()) {
                        time1.setText(getTime(player.getCurrentPosition()) + ":" + format.format(player.getCurrentPosition()).substring(14));
                        time2.setText(getTime(player.getDuration()) + ":" + format.format(player.getDuration()).substring(14));
                    }

                    if ((player.getDuration() - player.getCurrentPosition()) / 1000 == 1) {
                        flag = false;//播放结束跳出
                        sharedBar.setVisibility(View.VISIBLE);
                        //播放完成了 调用加积分接口
                        save("2", "0");
                    }

                    //////TAG-Share actiontype 暂时传""
                    if (exam_count > 0 && flag)//有考试、且正在播放的时候才进入判断
                    {

                        //有考试
                        for (int i = 0; i < exam_count; i++) {
                            if (player.getCurrentPosition() / 1000 == Integer.parseInt(ConstantSet.exam_time.get(i)))//到达第i次考试时间
                            {
                                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                    exitFullScreen();
                                }
                                Log.i("TAG-Exam", "进入考试");
                                //howShortToast(ConstantSet.homeAddress+ConstantSet.exam_url);
                                //
                                WebViewDialog dialog = new WebViewDialog(VideoActivity.this, handler, ConstantSet.homeAddress + ConstantSet.exam_url.get(i),
                                        "考试", 0, null, player, "");

                                Window window = dialog.getWindow();
                                window.setGravity(Gravity.BOTTOM);
                                dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                                dialog.show();
                                //将考试时间 设置到目前播放位置之前 表示已进行过考试
                                //不设置 将造成重复出现考试试题
                                int time = Integer.parseInt(ConstantSet.exam_time.get(i)) - 1;//单位s
                                ConstantSet.exam_time.set(i, String.valueOf(time));
                                player.pause();
                                flag = false;
                                //考试返回后 按钮不显示 原因暂时未找到
                                if (VideoActivity.this.isFullScreen) {
                                    startBt.setVisibility(View.VISIBLE);
                                    videoName.setVisibility(View.VISIBLE);
                                    videoBar.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                } catch (IllegalStateException e) {
                    // TODO: handle exception
                }
            }

            if (msg.arg1 == 1001) {
                if (msg.obj != null) {
                    videoName.setText(msg.obj.toString());
                    back_fullscreem.setVisibility(View.VISIBLE);
                }
                currentPosition = msg.what;//播放位置
                if (msg.arg2 == 0)//考试次数
                {
//                    intRxamPosition=10000000;
                    exam_count = 0;
                } else {
//                    intRxamPosition = msg.arg2;//考试时间点--->改成考试次数
                    exam_count = msg.arg2;
                }
            } else if (msg.arg1 == 10086) {
                startBt.setVisibility(View.INVISIBLE);
                videoName.setVisibility(View.INVISIBLE);
                videoBar.setVisibility(View.INVISIBLE);
                back_fullscreem.setVisibility(View.INVISIBLE);
                Log.i("TAG-Runnable", "5s 后隐藏按钮");
            } else if (msg.arg1 == 10010) {
                //考试页面返回时 传回
                //时间、进度不更新等问题
                Log.i("TAG-10010", "显示按钮");
                if (VideoActivity.this.isFullScreen) {
                    //控制按钮
                    show();

                }
                flag = true;//继续播放 true更新进度条
                setCurreProgress();
            } else if (msg.arg1 == 10011)//暂停
            {
                Log.i("TAG-PlayerPause", "暂停了");
                if (player == null) {
                    return;
                } else {
                    if (player.isPlaying()) {
                        Log.i("TAG-PlayerPause2", "暂停了");
                        startBt.setImageResource(R.mipmap.stop_img);
                        player.pause();
                        flag = false;
                    }
                }
            }
        }
    };


    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = (MyApplication) getApplication();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video);

        try {
            if (getIntent().hasExtra("class_id")) {
                if (!getIntent().getStringExtra("class_id").isEmpty()) {
                    mClassId = getIntent().getStringExtra("class_id");
                    Log.i("TAG-ClassId", mClassId);
                    ConstantSet.class_id = mClassId;
                }
            } else {
                return;
            }
        } catch (Exception e) {

        }
//        ShowMaskDialogUtils.showMaskDialog(VideoActivity.this,"videoFirstFile","videoFirst","Guide.Video.Share");

//        SaveString save=new SaveString(VideoActivity.this);
//        String videoFirst=save.getPhoneNumber("videoFirstFile","videoFirst");
//
//        if(videoFirst==null||videoFirst.equals("")) {
//            MaskDialog dialog = new MaskDialog(VideoActivity.this,"videoFirstFile","videoFirst","Guide.Video.Share");
//
//            Window window = dialog.getWindow();
//            window.setGravity(Gravity.CENTER);
//            dialog.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//            dialog.show();
//
//        }

        initView();
        initData();
        initEvent();

    }

    @Override
    protected void initView() {

        viewPager = (ViewPager) findViewById(R.id.video_viewpager);
        surface = (SurfaceView) findViewById(R.id.playerSurfaceView);



        initViewHolder();


        startBt = (ImageView) findViewById(R.id.start_bt);

        progressBar = (SeekBar) findViewById(R.id.number_progress_bar);
        bufferProgressBar = (ProgressBar) findViewById(R.id.bufferProgressBar);

        expendBt = (ImageView) findViewById(R.id.expend_img);
        back_fullscreem = (ImageView) findViewById(R.id.back_fullscreem);
        playerLayout = (RelativeLayout) findViewById(R.id.rl_play);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerLayout.getLayoutParams();
        params.height = MyApplication.getScreenWidth() * 9 / 16;
        playerLayout.setLayoutParams(params);


        topBar = (LinearLayout) findViewById(R.id.top_bar);


        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);

        jianjieBt = (TextView) findViewById(R.id.jianjie_bt);
        muluBt = (TextView) findViewById(R.id.mulu_bt);
        tiwenBt = (TextView) findViewById(R.id.tiwen_bt);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);

        back = (ImageView) findViewById(R.id.back);

        sharedImg = (ImageView) findViewById(R.id.shared_bt);
        collectImg = (ImageView) findViewById(R.id.collect_bt);

        videoTitle = (TextView) findViewById(R.id.video_title);
        // videoTitle.setText(ConstantSet.videoTitle);
        videoName = (TextView) findViewById(R.id.video_name_visible);

        videoBar = (LinearLayout) findViewById(R.id.video_bar);


        videoImg = (ImageView) findViewById(R.id.video_img);
        xuexiBt = (TextView) findViewById(R.id.xuexi_bt);
        img4 = (ImageView) findViewById(R.id.img4);

        sharedBar = (LinearLayout) findViewById(R.id.shared_bar);
        wx_bt = (ImageView) findViewById(R.id.wx_bt);
        wx_friend_bt = (ImageView) findViewById(R.id.wx_friend_bt);
        sina_bt = (ImageView) findViewById(R.id.sina_bt);

        replayer = (ImageView) findViewById(R.id.reply_btn);
        playNext = (ImageView) findViewById(R.id.play_next);
        guide_iv = (ImageView) findViewById(R.id.guide_iv);//新手引导
        btnGetCode = (TextView) findViewById(R.id.btnGetCode);
    }

    String mClassId;

    @Override
    protected void initData() {

        lists = new ArrayList<View>();

        getMyData();
        isCollect();


//        String tag = getIntent().getStringExtra("My");
//        if (tag.equalsIgnoreCase("My")||tag.equalsIgnoreCase(""))
//        {
//            //我的课程页面
//            getMyData();
//        }else {
//            getData();
//        }
    }

    @Override
    protected void initEvent() {

        sharedBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //SharedSdkUtils share=new SharedSdkUtils(VideoActivity.this,null);

            }
        });
        //视频课程分享 传入impower_id  还是class id？
        wx_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedSdkUtils share = new SharedSdkUtils(VideoActivity.this, null,
                        //Action_type
                        "share.course",
                        //Target_id
                        ConstantSet.impower_id);
                share.sharedToWeixin1("福建奔驰", "超棒的视频，大家一起来看吧", ConstantSet.sharedImageUrl, "http://www.mbmpv.com.cn/#page1");
            }
        });
        wx_friend_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedSdkUtils share = new SharedSdkUtils(VideoActivity.this, null,
                        //Action_type
                        "share.course",
                        //Target_id

                        ConstantSet.impower_id);
                share.sharedToFriend1("超棒的视频，大家一起来看吧", ConstantSet.sharedImageUrl, "http://www.mbmpv.com.cn/#page1");
            }
        });
        sina_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedSdkUtils share = new SharedSdkUtils(VideoActivity.this, null,
                        //Action_type
                        "share.course",
                        //Target_id

                        ConstantSet.impower_id);
                share.sharedToSina("超棒的视频，大家一起来看吧", ConstantSet.sharedImageUrl, "http://www.mbmpv.com.cn/#page1");
            }
        });


        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag) {
                    if (player.isPlaying()) {

                        startBt.setImageResource(R.mipmap.stop_img);
                        player.pause();
                        flag = false;
                    }
                } else if (!flag) {
                    startBt.setImageResource(R.mipmap.start_img);
                    player.start();
                    flag = true;

                    setCurreProgress();

                }


            }
        });

        collectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFollow) {
                    cancel();
                } else {
                    guanzhu();
                }
            }
        });


        expendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG-ExitFullScreem", "点击了全屏键");

                if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    enterFullScreen();
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    exitFullScreen();
                    back_fullscreem.setVisibility(View.INVISIBLE);
                }
            }
        });

        back_fullscreem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//                    enterFullScreen();
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    exitFullScreen();
                    back_fullscreem.setVisibility(View.INVISIBLE);
                }
            }
        });

        jianjieBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                jianjieBt.setTextColor(Color.parseColor("#333333"));

                tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                xuexiBt.setTextColor(Color.parseColor("#C4C4C4"));
                img1.setImageResource(R.mipmap.touming_img);
                img2.setImageResource(R.mipmap.heixian_img);
                img3.setImageResource(R.mipmap.touming_img);
                img4.setImageResource(R.mipmap.touming_img);


                viewPager.setCurrentItem(1);

            }
        });


        muluBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                muluBt.setTextColor(Color.parseColor("#333333"));
                tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                xuexiBt.setTextColor(Color.parseColor("#C4C4C4"));

                img1.setImageResource(R.mipmap.heixian_img);
                img2.setImageResource(R.mipmap.touming_img);
                img3.setImageResource(R.mipmap.touming_img);
                img4.setImageResource(R.mipmap.touming_img);

                viewPager.setCurrentItem(0);
            }
        });


        tiwenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                SaveString save=new SaveString(VideoActivity.this);
//                String videoFirst=save.getPhoneNumber("tiWenFirstFile","tiWenFirst");
//
//                if(videoFirst==null||videoFirst.equals("")) {
//                    Log.i("MASKDIALOG", "执行了！");
//
//                    MaskDialog dialog1 = new MaskDialog(VideoActivity.this,"tiWenFirstFile","tiWenFirst","Guide.Question");
//
//                    Window window1 = dialog1.getWindow();
//                    window1.setGravity(Gravity.CENTER);
//                    dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//                    dialog1.show();
//                }

                jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                tiwenBt.setTextColor(Color.parseColor("#333333"));
                xuexiBt.setTextColor(Color.parseColor("#C4C4C4"));

                img1.setImageResource(R.mipmap.touming_img);
                img2.setImageResource(R.mipmap.touming_img);
                img3.setImageResource(R.mipmap.heixian_img);
                img4.setImageResource(R.mipmap.touming_img);


                viewPager.setCurrentItem(2);
            }
        });

        xuexiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                xuexiBt.setTextColor(Color.parseColor("#333333"));

                img1.setImageResource(R.mipmap.touming_img);
                img2.setImageResource(R.mipmap.touming_img);
                img3.setImageResource(R.mipmap.touming_img);
                img4.setImageResource(R.mipmap.heixian_img);

                viewPager.setCurrentItem(3);

            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                    muluBt.setTextColor(Color.parseColor("#333333"));
                    tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                    xuexiBt.setTextColor(Color.parseColor("#C4C4C4"));

                    img1.setImageResource(R.mipmap.heixian_img);
                    img2.setImageResource(R.mipmap.touming_img);
                    img3.setImageResource(R.mipmap.touming_img);
                    img4.setImageResource(R.mipmap.touming_img);
                } else if (position == 1) {
                    muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                    jianjieBt.setTextColor(Color.parseColor("#333333"));

                    tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                    xuexiBt.setTextColor(Color.parseColor("#C4C4C4"));
                    img1.setImageResource(R.mipmap.touming_img);
                    img2.setImageResource(R.mipmap.heixian_img);
                    img3.setImageResource(R.mipmap.touming_img);
                    img4.setImageResource(R.mipmap.touming_img);
                } else if (position == 2) {
                    jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                    muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                    tiwenBt.setTextColor(Color.parseColor("#333333"));
                    xuexiBt.setTextColor(Color.parseColor("#C4C4C4"));

                    img1.setImageResource(R.mipmap.touming_img);
                    img2.setImageResource(R.mipmap.touming_img);
                    img3.setImageResource(R.mipmap.heixian_img);
                    img4.setImageResource(R.mipmap.touming_img);


//                    SaveString save=new SaveString(VideoActivity.this);
//                    String videoFirst=save.getPhoneNumber("tiWenFirstFile","tiWenFirst");
//                    if(videoFirst==null||videoFirst.equals("")) {
//                        //新手引导
//                        ShowMaskDialogUtils.showMaskDialog(VideoActivity.this,"tiWenFirstFile","tiWenFirst","Guide.Question");
//                    }
//                    ShowMaskDialogUtils.showMaskDialog(VideoActivity.this,"tiWenFirstFile","tiWenFirst","Guide.Question");
//
//                    if(videoFirst==null||videoFirst.equals("")) {
//                        Log.i("MASKDIALOG", "执行了2！");
//
//                        MaskDialog dialog1 = new MaskDialog(VideoActivity.this,"tiWenFirstFile","tiWenFirst","Guide.Question");
//
//                        Window window1 = dialog1.getWindow();
//                        window1.setGravity(Gravity.CENTER);
//                        dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//                        dialog1.show();
//                    }
                } else {
                    jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                    muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                    tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                    xuexiBt.setTextColor(Color.parseColor("#333333"));

                    img1.setImageResource(R.mipmap.touming_img);
                    img2.setImageResource(R.mipmap.touming_img);
                    img3.setImageResource(R.mipmap.touming_img);
                    img4.setImageResource(R.mipmap.heixian_img);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // showShortToast(player.getCurrentPosition()+"");
//                if(!isComplete) {
//                    save("1", player.getCurrentPosition() / 1000 + "");  //1是未完成 2是完成 0是未播放
//                }
                SPUtils.putValue(VideoActivity.this, "Settings", "video_id", 5000);
                VideoActivity.this.finish();

            }
        });

//        sharedImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SharedDialog dialog = new SharedDialog(VideoActivity.this,"video","http://www.mbmpv.com.cn/#page1", "");
//
//                Window window = dialog.getWindow();
//                window.setGravity(Gravity.BOTTOM);
//                dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
//                dialog.show();
//
//            }
//        });

        playerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG-Video", "点击了视频界面");
                show();

            }
        });

//      重播
        replayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.seekTo(0);
                player.start();

                sharedBar.setVisibility(View.INVISIBLE);
                //考试time要复原
            }
        });

        //下一段
        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView1.playNext();
            }
        });


        //新手引导
        guide_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出对话框 选择字段
                PopupMenu popup = new PopupMenu(VideoActivity.this, guide_iv, 300);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popmenu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.special_topic) {
                            //视频引导
//                            showShortToast("视频");
                            ShowMaskDialogUtils.showMaskDialog_Btn(VideoActivity.this, "videoFirstFile", "videoFirst", "Guide.Video.Share");
                        } else {
                            //提问引导
//                            showShortToast("提问");
                            ShowMaskDialogUtils.showMaskDialog_Btn(VideoActivity.this, "tiWenFirstFile", "tiWenFirst", "Guide.Question");
                        }
                        return false;
                    }
                });

                popup.show(); //showing popup menu
            }

//                LayoutInflater inflater = getLayoutInflater();
//                View view1 = inflater.inflate(R.layout.guidelist_dialog, null);
//                final Dialog mAlertDialog = new Dialog(VideoActivity.this, R.style.CustomDialogStyle);
//                mAlertDialog.setContentView(view1);
//                mAlertDialog.show();
//
//
//                view1.findViewById(R.id.guide1).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        ShowMaskDialogUtils.showMaskDialog_Btn(VideoActivity.this,"videoFirstFile","videoFirst","Guide.Video.Share");
//
//                        mAlertDialog.dismiss();
//                    }
//                });
//                view1.findViewById(R.id.guide1).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        ShowMaskDialogUtils.showMaskDialog_Btn(VideoActivity.this,"tiWenFirstFile","tiWenFirst","Guide.Question");
//
//                        mAlertDialog.dismiss();
//                    }
//                });

        });

    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnGetCode.setText("" + millisUntilFinished / 1000 + "s后播放下一段");
        }

        @SuppressLint("NewApi")
        @Override
        public void onFinish() {
            videoView1.playNext();
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        Log.i("TAG-Surface", "Surface创建OK了");


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        Log.i("TAG-Surface", "Surface销毁了");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
            player.release();
            myApplication.getDRMServer().disconnectCurrentStream();
        }
        super.onDestroy();
        flag = false;
        //Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音

        //切换课程时 重置index
        SPUtils.putValue(VideoActivity.this, "Settings", "video_child_index", 5000);
        SPUtils.putValue(VideoActivity.this, "Settings", "video_index", 5000);
    }


    //进入全屏
    public void enterFullScreen() {
        if (this.isFullScreen) {
            return;
        }
//	        this.videoSmallWidth = MyApplication.getScreenWidth();
//	        this.videoSmallHeight = this.videoView.getHeight()

        expendBt.setImageResource(R.mipmap.expend_small_img);
        //显示返回按钮
        back_fullscreem.setVisibility(View.VISIBLE);
        this.isFullScreen = true;
        topBar.setVisibility(View.GONE);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT
        );
        this.playerLayout.setLayoutParams(lp);
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
//
//	        DisplayMetrics dm = new DisplayMetrics();
//	        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
//	        this.mVideoPlayer.setLayoutParams(new LayoutParams(
//	                    dm.widthPixels, 
//	                    dm.heightPixels
//	                ));
        // this.mediaController.setFullScreenText("退出全屏");

//	      this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

    }

    //	    //退出全屏
    public void exitFullScreen() {
        if (!this.isFullScreen) {
            return;
        }
        this.isFullScreen = false;
        topBar.setVisibility(View.VISIBLE);
        back_fullscreem.setVisibility(View.INVISIBLE);
        expendBt.setImageResource(R.mipmap.expend_big_img);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                MyApplication.getScreenWidth(),
                MyApplication.getScreenWidth() / 2
        );
        this.playerLayout.setLayoutParams(lp);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE); 
/*	        this.videoView.setLayoutParams(new LayoutParams(
                    this.videoSmallWidth, 
	                this.videoSmallHeight
	            ));*/

        //  this.mediaController.setFullScreenText("全屏");

//	      this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }


    public void setCurreProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1000);
                        Message msg = Message.obtain();
                        msg.arg1 = 100;
                        handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    //来电处理

    protected void onPause() {
        if (player != null) {
            if (player.isPlaying()) {
                currentPosition = player.getCurrentPosition();
                player.pause();
                flag = false;

            }
        }
        Log.i("TAG-Video", "执行了onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("TAG-Video", "执行了onStop");
        super.onStop();
    }

    protected void onResume() {
        surfaceHolder = surface.getHolder();//SurfaceHolder是SurfaceView的控制接口
        surfaceHolder.addCallback(this); //因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//Surface类型

        if (player != null) {
            if (!player.isPlaying()) {
                player.seekTo(currentPosition);
                player.start();
            }
        }

        sharedBar.setVisibility(View.INVISIBLE);

        Log.i("TAG-Video", "执行了onResume");

        super.onResume();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        surfaceHolder = surface.getHolder();//SurfaceHolder是SurfaceView的控制接口
        surfaceHolder.addCallback(this); //因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//Surface类型
    }

    public String getTime(long time) {
        String str = ((int) time / (3600 * 1000)) + "";
        return str;
    }

    //我的页面跳转  这个OK的
    public void getMyData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "class/getdetail?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.i("Video_info", response);
                try {
                    Gson gson = new Gson();
                    resultLessonKc = gson.fromJson(response, new TypeToken<ResultDetail>() {
                    }.getType());
                    //showShortToast(response);
                    if (resultLessonKc.getStatus().equalsIgnoreCase("1")) {
                        videoView1 = new VideoView1(VideoActivity.this, resultLessonKc.getData().getSection(), player, handler);
                        Log.i("TAG-VideoView1", gson.toJson(resultLessonKc.getData().getSection()) );
                        videoView1.setOnVideoDataListener(new OnVideoDataListener() {
                            @Override
                            public void reSetPlayerData(String store_value, String store_type, String store_config) {
//                                Log.i("TAG-Interface", "回调了！"+"store_value="+store_value+"   store_type="+store_type+"  store_config="+store_config);
                                reSetPlayer(store_value, store_type, store_config);
                            }
                        });


                        lists.add(videoView1.getView());
                        lists.add(new VideoView2(VideoActivity.this, resultLessonKc.getData().getInfo()).getView());
                        lists.add(new VideoView3(VideoActivity.this, mClassId, handler).getView());
                        videoView4 = new VideoView4(VideoActivity.this, handler);
                        lists.add(videoView4.getView());

                        adapter = new VideoViewPagerAdapter(lists, VideoActivity.this);
                        viewPager.setAdapter(adapter);


                        videoTitle.setText(resultLessonKc.getData().getInfo().getCourse_name());
                        LoadImgUtils.setImage(VideoActivity.this, resultLessonKc.getData().getInfo().getCourse_album(), videoImg);
                        //tiwenBt.setText("提问（"+resultLessonKc.getData().getNum_comment()+")");

//                    ConstantSet.impower_id=resultLessonKc.getData().getInfo().getImpower_id();
//                        ConstantSet.class_id = resultLessonKc.getData().getInfo().getClassArr().get(0).getClass_id(); //我的考试页面根据class_id获取考试信息
                        ConstantSet.class_data_url = ConstantSet.homeAddress + "course/getdownload?class_id=" + ConstantSet.class_id;
                        if ((resultLessonKc.getData().getInfo()).getIs_follow().equals("0")) {
                            collectImg.setImageResource(R.mipmap.collect_normal);
                            isFollow = false;
                        } else {
                            collectImg.setImageResource(R.mipmap.collect_img);
                            isFollow = true;
                        }

                        getClassStudyData(videoView4, mClassId);
                    }
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(VideoActivity.this, "网络请求失败-CourseDetail", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
//                map.put("course_id", ConstantSet.course_id);//后期改成impower_id
                map.put("class_id", mClassId);
                if (ConstantSet.user != null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    /**
     * 创建时间 16/9/20
     * auther Hades
     * 描述  设置player资源 配置cc账号
     **/
    private void reSetPlayer(String store_value, String store_type, String store_config) {
//        initViewHolder();
        initPlayer();
        player.setDRMServerPort(myApplication.getDrmServerPort());
        try {

            if (store_type.equalsIgnoreCase("2")) {// 播放线上视频
                //此处进行加密 未加密判断
                if (store_config.equalsIgnoreCase("2")) {
                    //设置数据
                    player.setVideoPlayInfo(store_value, ConstantSet.CC_Account_id, ConstantSet.CC_Account_Key, VideoActivity.this);
                } else if (store_config.equalsIgnoreCase("1")) {
                    player.setVideoPlayInfo(store_value, ConstantSet.CC_Account_NO_id, ConstantSet.CC_Account_NO_Key, VideoActivity.this);
                }

                player.prepareAsync();//准备
                Message msg = Message.obtain();
                msg.arg1 = 1001;
                handler.sendMessage(msg);


            } else {// 播放本地已下载视频

                if (android.os.Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

//                    path = Environment.getExternalStorageDirectory() + "/".
//                            concat(ConfigUtil.DOWNLOAD_DIR).concat("/").concat(videoId).concat(MediaUtil.PCM_FILE_SUFFIX);
//
//                    if (!new File(path).exists()) {
//                        finish();
//                    }
                }
            }

        } catch (IllegalArgumentException e) {
            Log.e("player error", e.getMessage());
        } catch (SecurityException e) {
            Log.e("player error", e.getMessage());
        } catch (IllegalStateException e) {
            Log.e("player error", "illegal", e);
        }
    }

    private void initPlayer() {

        if (player != null) {
            player.release();
        }
        player = new DWMediaPlayer();
        player.reset();
        player.setDisplay(surfaceHolder); //跳转后被销毁了
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setScreenOnWhilePlaying(true);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                videoImg.setVisibility(View.GONE);
                sharedBar.setVisibility(View.INVISIBLE);
                player.start();
                player.seekTo(currentPosition * 1000);
                isComplete = false;
                Log.i("TAG-SetPlayer", "player,Prepared");
                show();
                flag = true;
                startBt.setImageResource(R.mipmap.start_img);
                Message msg = Message.obtain();
                msg.arg1 = 101;
                handler.sendMessage(msg);

                setCurreProgress();
            }
        });

        //播放完成
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                // Toast.makeText(VideoActivity.this,"baocu",Toast.LENGTH_SHORT).show();
                sharedBar.setVisibility(View.VISIBLE);
                save("2", "0");
                startBt.setImageResource(R.mipmap.stop_img);
                player.pause();
                isComplete = true;
                flag = false;
                time.start();
            }
        });

        player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            public void onSeekComplete(MediaPlayer m) {
                m.start();
                flag = true;

                setCurreProgress();

            }
        });

        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                startBt.setImageResource(R.mipmap.stop_img);
                return false;
            }
        });
        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                progressBar.setSecondaryProgress(percent);
            }
        });

        player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case DWMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if (player.isPlaying()) {
                            bufferProgressBar.setVisibility(View.VISIBLE);
                        }
                        break;
                    case DWMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        bufferProgressBar.setVisibility(View.GONE);
                        break;
                }
                return false;
            }
        });


    }

    private void initViewHolder() {
        surfaceHolder = surface.getHolder();//SurfaceHolder是SurfaceView的控制接口
        surfaceHolder.addCallback(this); //因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
        // surfaceHolder.setFixedSize(320, 220);//显示的分辨率,不设置为视频默认
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//Surface类型
    }



    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode)
            return true;
        return super.onKeyDown(keyCode, event);
    }


    public void isCollect() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/isfollow?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.d("Resopnse  ", response);
                // showShortToast(response);
                if (response.length() > 10) {

                    Gson gson = new Gson();

                    Sign resultLessonKc = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());


                    if (resultLessonKc.getMessage().equals("未关注该课程")) {
                        collectImg.setImageResource(R.mipmap.collect_normal);
                        isFollow = false;

                    } else {
                        collectImg.setImageResource(R.mipmap.collect_img);
                        isFollow = true;
                    }

//
//                    list1=resultLessonKc.getData().getList();
//                    adapter1=new KechengListAdapter(list1,getActivity());
//                    listView1.setAdapter(adapter1);

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(VideoActivity.this, "网络请求失败-是否关注", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("course_id", mClassId);//后期改成course_id
                if (ConstantSet.user != null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    //关注
    public void guanzhu() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "actions/doactions?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.i("Resopnse  ", response);
                //showShortToast(response);
                if (response.length() > 10) {

                    Gson gson = new Gson();

                    WatchOnBean resultLessonKc = gson.fromJson(response, WatchOnBean.class);

                    collectImg.setImageResource(R.mipmap.collect_img);
                    isFollow = true;

                    Toast.makeText(VideoActivity.this, resultLessonKc.getMessage(), Toast.LENGTH_SHORT).show();

//                    list1=resultLessonKc.getData().getList();
//                    adapter1=new KechengListAdapter(list1,getActivity());
//                    listView1.setAdapter(adapter1);

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(VideoActivity.this, "网络请求失败-关注", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type", "follow");
                map.put("obj_id", resultLessonKc.getData().getInfo().getImpower_id());
                map.put("action_type", "user");
                map.put("target_type", "follow");
                if (ConstantSet.user != null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }

                map.put("okey", Md5Utils.md5("moocuserfollowfollow" + resultLessonKc.getData().getInfo().getImpower_id()));
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    //取消关注
    public void cancel() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "actions/doactions?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.d("Resopnse  ", response);
                // showShortToast(response);
                if (response.length() > 30) {

                    Gson gson = new Gson();

//                    Sign resultLessonKc = gson.fromJson(response, new TypeToken<Sign>() {
//                    }.getType());
                    WatchOnBean resultLessonKc = gson.fromJson(response, WatchOnBean.class);
                    isFollow = false;

                    collectImg.setImageResource(R.mipmap.collect_normal);

                    Toast.makeText(VideoActivity.this, resultLessonKc.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    list1=resultLessonKc.getData().getList();
//                    adapter1=new KechengListAdapter(list1,getActivity());
//                    listView1.setAdapter(adapter1);

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(VideoActivity.this, "网络请求失败-取消关注", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type", "cancelfollow");
                map.put("obj_id", resultLessonKc.getData().getInfo().getImpower_id());
                map.put("action_type", "user");
                map.put("target_type", "cancelfollow");
                if (ConstantSet.user != null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }

                map.put("okey", Md5Utils.md5("moocusercancelfollowcancelfollow" + resultLessonKc.getData().getInfo().getImpower_id()));
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    public void save(final String isComplete, final String watch_time) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "class/savewatchvideo?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.d("Resopnse  ", response);
                // showShortToast(response);
                if (response.length() > 30) {

                    Gson gson = new Gson();

//                    Sign resultLessonKc = gson.fromJson(response, new TypeToken<Sign>() {
//                    }.getType());

                    isFollow = false;

                    collectImg.setImageResource(R.mipmap.collect_normal);


//
//                    list1=resultLessonKc.getData().getList();
//                    adapter1=new KechengListAdapter(list1,getActivity());
//                    listView1.setAdapter(adapter1);

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                // Toast.makeText(VideoActivity.this, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("class_id", resultLessonKc.getData().getInfo().getClass_id());

                map.put("section_uid", ConstantSet.section_uid);

                map.put("watch_time", watch_time);
                map.put("watch_status", isComplete);
                if (ConstantSet.user != null) {
                    map.put("user_id", ConstantSet.user.getUid());
                    map.put("okey", Md5Utils.md5("moocclasssavewatchvideo" + ConstantSet.user.getUid() + resultLessonKc.getData().getInfo().getClass_id() + ConstantSet.section_uid));
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    //显示隐藏

    public void show() {
        startBt.setVisibility(View.VISIBLE);
        videoBar.setVisibility(View.VISIBLE);
        videoName.setVisibility(View.VISIBLE);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            //  不是横屏的情况下隐藏左上角返回按钮
            back_fullscreem.setVisibility(View.INVISIBLE);
        } else {
            back_fullscreem.setVisibility(View.VISIBLE);
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);

                    Message msg = Message.obtain();
                    msg.arg1 = 10086;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    ClassResultDetail resultLessonKc2;
    List<ZhangBean> zhangBeanList2;

    //获取对应课程资料
    public void getClassStudyData(final VideoView4 view1, String classid) {
        StringRequest rq = new StringRequest(Request.Method.GET, ConstantSet.homeAddress + "course/getdownload?class_id=" + classid, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                try {
//                    Log.i("Class_id-result", "  response= "+ConstantSet.homeAddress + "course/getdownload?class_id="+ConstantSet.class_id);
                    Log.i("Class_id-result", "  response= " + response);
                    Gson gson = new Gson();
                    resultLessonKc2 = gson.fromJson(response, new TypeToken<ClassResultDetail>() {
                    }.getType());

                    if (resultLessonKc2.getStatus() == 1) {
                        zhangBeanList2 = resultLessonKc2.getData();
                        int size = zhangBeanList2.size();
                        int k = 0;
                        for (int i = 0; i < size; i++) {
                            if (zhangBeanList2.get(i).getChild().isEmpty()) {
                                //第i章没有节
                                if (zhangBeanList2.get(i).getSource().isEmpty()) {
//                                   第i章没有 资料
                                } else {
                                    k = 1;
                                }

                            } else { //第i章有节
                                int jieSize = zhangBeanList2.get(i).getChild().size();
                                for (int j = 0; j < jieSize; j++) {
                                    if (zhangBeanList2.get(i).getChild().get(j).getSource().isEmpty()) //第j节无资料
                                    {

                                    } else {
                                        k = 1;
                                    }
                                }
                            }
                        }

                        if (k == 0) {
                            Log.i("Class_id-result", "无课程资料");
                            Message msg = Message.obtain();
                            msg.arg1 = 10100;
                            view1.mhandler.sendMessage(msg);
                        }

                    }

                } catch (Exception e) {

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(VideoActivity.this, "网络请求失败-学习资料", Toast.LENGTH_SHORT).show();
            }
        });
        MyApplication.getRq().add(rq);
    }


}
