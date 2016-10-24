package cn.automooc.com.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.BaseActivity;
import cn.automooc.com.R;
import cn.automooc.com.adapter.VideoViewPagerAdapterFirst;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.ResultDetail;
import cn.automooc.com.bean.Sign;
import cn.automooc.com.bean.WatchOnBean;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.LoadImgUtils;
import cn.automooc.com.utils.Md5Utils;
import cn.automooc.com.utils.SaveUser;
import cn.automooc.com.utils.ToastUtils;
import cn.automooc.com.view.CourseIntroduceView;
import cn.automooc.com.view.ListOfCommentView;
import cn.automooc.com.view.ListOfCourseView;

public class CourseActivity extends BaseActivity {

    ViewPager viewPager;
    VideoViewPagerAdapterFirst adapter;
    List<View> lists;
    
    ImageView videoImg;
    
    TextView jianjieBt;
    TextView muluBt;
    TextView tiwenBt;
    
    ImageView img1;
    ImageView img2;
    ImageView img3;
    
    
    ImageView back;

//    ProgressDialog dialog;
    
    TextView videoTitle;
    
    private static int REFERSH_COLLECTION = 2000;
    
    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.arg1 == REFERSH_COLLECTION){
                refreshCollection();
            }
            return false;
        }
    });

    private void refreshCollection() {
        if (isFollow){
            collectImg.setImageResource(R.mipmap.collect_img);
        }else {
            collectImg.setImageResource(R.mipmap.collect_normal);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_first);
        initView();
        initData();
        initEvent();

    }

    ImageView collectImg;
    boolean isFollow = false;

    @Override
    protected void initView() {
        
        viewPager= (ViewPager) findViewById(R.id.video_viewpager);
        videoImg= (ImageView) findViewById(R.id.video_img);

      /*  LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) videoImg.getLayoutParams();
        params.height=MyApplication.getScreenWidth()*2/5;
        videoImg.setLayoutParams(params);*/
        
        jianjieBt= (TextView) findViewById(R.id.jianjie_bt);
        muluBt= (TextView) findViewById(R.id.mulu_bt);
        tiwenBt= (TextView) findViewById(R.id.tiwen_bt);
        
        img1= (ImageView) findViewById(R.id.img1);
        img2= (ImageView) findViewById(R.id.img2);
        img3= (ImageView) findViewById(R.id.img3);

        back= (ImageView) findViewById(R.id.back);

        videoTitle= (TextView) findViewById(R.id.video_title);

        //videoTitle.setText(ConstantSet.videoTitle);

//        dialog = new ProgressDialog(this);
//
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.CENTER);
//        dialog.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//        dialog.show();

        if(ConstantSet.confiMap!=null) {
            if (ConstantSet.confiMap.get("App.Switch.Course.Evaluate.Show").equals("1")) {
                tiwenBt.setVisibility(View.GONE);
                img3.setVisibility(View.GONE);
            }
        }

        collectImg = (ImageView) findViewById(R.id.collect_iv);

    }

    @Override
    protected void initData() {
        
        lists=new ArrayList<View>();

        getData();
        isCollect();
        

    }



    @Override
    protected void initEvent() {

        jianjieBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                jianjieBt.setTextColor(Color.parseColor("#333333"));
                muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                
                img1.setImageResource(R.mipmap.heixian_img);
                img2.setImageResource(R.mipmap.touming_img);
                img3.setImageResource(R.mipmap.touming_img);
                
                
                viewPager.setCurrentItem(0);
                
            }
        });
        
        
        muluBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                muluBt.setTextColor(Color.parseColor("#333333"));
                tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                img1.setImageResource(R.mipmap.touming_img);
                img2.setImageResource(R.mipmap.heixian_img);
                img3.setImageResource(R.mipmap.touming_img);
                
                viewPager.setCurrentItem(1);
            }
        });
        
        
        tiwenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                tiwenBt.setTextColor(Color.parseColor("#333333"));

                img1.setImageResource(R.mipmap.touming_img);
                img2.setImageResource(R.mipmap.touming_img);
                img3.setImageResource(R.mipmap.heixian_img);
                
                viewPager.setCurrentItem(2);
            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {

                if(position==0)
                {
                    jianjieBt.setTextColor(Color.parseColor("#333333"));
                    muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                    tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));

                    img1.setImageResource(R.mipmap.heixian_img);
                    img2.setImageResource(R.mipmap.touming_img);
                    img3.setImageResource(R.mipmap.touming_img); 
                }

                else if(position==1)
                {
                    jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                    muluBt.setTextColor(Color.parseColor("#333333"));
                    tiwenBt.setTextColor(Color.parseColor("#C4C4C4"));
                    img1.setImageResource(R.mipmap.touming_img);
                    img2.setImageResource(R.mipmap.heixian_img);
                    img3.setImageResource(R.mipmap.touming_img);
                }else 
                {
                    jianjieBt.setTextColor(Color.parseColor("#C4C4C4"));
                    muluBt.setTextColor(Color.parseColor("#C4C4C4"));
                    tiwenBt.setTextColor(Color.parseColor("#333333"));

                    img1.setImageResource(R.mipmap.touming_img);
                    img2.setImageResource(R.mipmap.touming_img);
                    img3.setImageResource(R.mipmap.heixian_img);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                CourseActivity.this.finish();
                
            }
        });

        collectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveUser save=new SaveUser(CourseActivity.this);
                ConstantSet.user=(save.getData("userFile","user"));

                if(ConstantSet.user == null)
                {
                    Toast.makeText(CourseActivity.this, "请登录...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CourseActivity.this, LoginAndRegisterActivity.class));
//                    ((CourseActivity) mContext).finish();

                }
                else if (ConstantSet.user.getUid().equalsIgnoreCase("0")){
                    Toast.makeText(CourseActivity.this, "请登录...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CourseActivity.this, LoginAndRegisterActivity.class));

                }else {

                    if (isFollow) {
                        cancel();
                    } else {
                        guanzhu();
                    }
                }
            }
        });
        
        
    }


    ResultDetail resultLessonKc;
    
    public void getData()
    {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/getdetail?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("impwer_id  ",response);
               // showLongToast(response);
                try {

                    Gson gson=new Gson();

                    resultLessonKc = gson.fromJson(response, new TypeToken<ResultDetail>() {
                    }.getType());
                    if (resultLessonKc.getStatus().equalsIgnoreCase("1")) {
                        videoTitle.setText(resultLessonKc.getData().getInfo().getCourse_name());
                        ConstantSet.impower_id = resultLessonKc.getData().getInfo().getImpower_id();
                        ConstantSet.home_course_id = resultLessonKc.getData().getInfo().getCourse_id();
                        lists.add(new CourseIntroduceView(CourseActivity.this, resultLessonKc.getData().getInfo()).getView());

                        lists.add(new ListOfCourseView(CourseActivity.this, resultLessonKc.getData().getSection()).getView());

                        if (ConstantSet.confiMap != null) {
                            if (ConstantSet.confiMap.get("App.Switch.Course.Evaluate.Show").equals("0")) {
                                lists.add(new ListOfCommentView(CourseActivity.this, resultLessonKc.getData().getInfo()).getView());
                            }
                        }
                        if (adapter == null) {
//                        dialog.colseDialog();
                        }

                        adapter = new VideoViewPagerAdapterFirst(lists, CourseActivity.this);
                        viewPager.setAdapter(adapter);


                        LoadImgUtils.setImage(CourseActivity.this, resultLessonKc.getData().getInfo().getCourse_album(), videoImg);

                        tiwenBt.setText("提问（" + resultLessonKc.getData().getNum_comment() + ")");

//
//                    list1=resultLessonKc.getData().getList();
//                    adapter1=new KechengListAdapter(list1,getActivity());
//                    listView1.setAdapter(adapter1);

                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(CourseActivity.this, "网络请求失败-Course", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("impower_id",ConstantSet.course_id);//后期改成impower_id了
                map.put("okey", Md5Utils.md5("mooccoursegetdetail"+ConstantSet.course_id));
                SaveUser save=new SaveUser(CourseActivity.this);
                ConstantSet.user=(save.getData("userFile","user"));
                if (ConstantSet.user != null){

                    if (!ConstantSet.user.getUid().equalsIgnoreCase("0")) {
                        Log.i("TAG-User", ConstantSet.user.getUid());
                        map.put("user_id", ConstantSet.user.getUid());
                    }
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
                Log.i("Guanzhu  ", response);
                //showShortToast(response);
                try {
                    Gson gson = new Gson();
                    WatchOnBean resultLessonKc = gson.fromJson(response, WatchOnBean.class);
                    if (resultLessonKc.getStatus() == 1) {
                        collectImg.setImageResource(R.mipmap.collect_img);
                        isFollow = true;
                        Toast.makeText(CourseActivity.this,resultLessonKc.getMessage(),Toast.LENGTH_SHORT).show();
                    }else {
//                        Toast.makeText(CourseActivity.this,resultLessonKc.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
//                Toast.makeText(CourseActivity.this, "网络请求失败-关注", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type", "impower");
                map.put("obj_id", resultLessonKc.getData().getInfo().getImpower_id() );
                map.put("action_type", "user");
                map.put("target_type", "follow");
                if (ConstantSet.user != null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }

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

                Log.d("TAG-Cancel  ", response);

                    Gson gson = new Gson();
                try{
                    WatchOnBean resultLessonKc = gson.fromJson(response, WatchOnBean.class);
                    if (resultLessonKc.getStatus() == 1) {
                        isFollow = false;
                        Message msg = Message.obtain();
                        msg.arg1 = REFERSH_COLLECTION;
                        mHandler.sendMessage(msg);

                        Toast.makeText(CourseActivity.this, resultLessonKc.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    ToastUtils.showTextToast("出错了!请稍后再试!", CourseActivity.this);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
//                Toast.makeText(CourseActivity.this, "网络请求失败-取消关注", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("obj_type", "impower");
                map.put("obj_id",  resultLessonKc.getData().getInfo().getImpower_id());
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


    public void isCollect() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/isfollow?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.d("IsCollect  ", response);
                // showShortToast(response);
                try {

                    Gson gson = new Gson();

                    Sign resultLessonKc = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());

                    if (resultLessonKc.getStatus() == 1) {
                        isFollow = false;
                        collectImg.setImageResource(R.mipmap.collect_normal);
                    } else {
                        isFollow = true;
                        collectImg.setImageResource(R.mipmap.collect_img);

                    }

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
//                Toast.makeText(CourseActivity.this, "网络请求失败-是否关注", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("course_id", ConstantSet.course_id);//后期改成course_id
                if (ConstantSet.user != null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
    
}
