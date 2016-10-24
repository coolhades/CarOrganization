package cn.automooc.com.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hades.libam.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.BaseActivity;
import cn.automooc.com.Fragment.HomeFragment;
import cn.automooc.com.Fragment.KeChengFragment;
import cn.automooc.com.Fragment.MyselfFragment;
import cn.automooc.com.R;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.ResultMyClass;
import cn.automooc.com.bean.ResultUser;
import cn.automooc.com.bean.Sign;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.SaveUser;
import cn.automooc.com.utils.SharedPreferencesHelper;
import cn.automooc.com.utils.ToastUtils;
import cn.automooc.com.widget.SignDialog;

public class HomeActivity extends BaseActivity implements View.OnClickListener{
    
   // ViewPager mViewPager;
   // HomeViewPagerAdapter adapter;
    HomeFragment mHomeFragment;
    KeChengFragment mKeChengFragment;
    MyselfFragment mMyselfFragment;

    FragmentManager manager;
    FragmentTransaction transaction;
    Fragment currentFragment;
    
    List<Fragment> lists;
    
    
   /*Banner*/
    ImageView homeImg;
    ImageView keChengImg;
    ImageView mySelfImg;
    
    TextView homeText;
    TextView keChengText;
    TextView mySelfTxet;
    
    /*Banner*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (!SPUtils.getValue(this, "Login", "isFirstLogin", false)){
            Intent i = new Intent(this, GuideActivity.class);
            startActivity(i);
            this.finish();
        }else {
            initView();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            if (savedInstanceState == null) {
                transaction.add(R.id.fragment_layout, mHomeFragment, "fragmentA");
                //transaction.replace(R.id.fragment_layout,fragmentA);
                transaction.commit();
                currentFragment = mHomeFragment;
            }
            initData();
            initEvent();
        }
    }

    @Override
    protected void initView() {

        lists=new ArrayList<Fragment>();
        
        mHomeFragment=new HomeFragment();
        mKeChengFragment=new KeChengFragment();
        mMyselfFragment=new MyselfFragment();
        lists.add(mHomeFragment);
        lists.add(mKeChengFragment);
        lists.add(mMyselfFragment);
        
//        mViewPager= (ViewPager) findViewById(R.id.home_viewpager);
//        
//        mViewPager.setOffscreenPageLimit(3);
        
        
        homeImg= (ImageView) findViewById(R.id.home_img);
        keChengImg= (ImageView) findViewById(R.id.kecheng_img);
        mySelfImg= (ImageView) findViewById(R.id.myself_img);
        
        homeText= (TextView) findViewById(R.id.home_text);
        keChengText= (TextView) findViewById(R.id.kecheng_text);
        mySelfTxet= (TextView) findViewById(R.id.myself_text);
        
        
        homeImg.setOnClickListener(this);
        keChengImg.setOnClickListener(this);
        mySelfImg.setOnClickListener(this);
        
        homeText.setOnClickListener(this);
        keChengText.setOnClickListener(this);
        mySelfTxet.setOnClickListener(this);


//        if(ConstantSet.sign==false) {
//            SignDialog dialog = new SignDialog(this);
//
//            Window window = dialog.getWindow();
//            window.setGravity(Gravity.CENTER);
//            dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
//            dialog.show();
//        }


//        getSignData();
        
    }

    @Override
    protected void initData() {

        getUserInfo();
        getMyClass();
//       adapter = new HomeViewPagerAdapter(getSupportFragmentManager(),lists);
//        mViewPager.setAdapter(adapter);
        
    }

    @Override
    protected void initEvent() {

//        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if(position==0)
//                {
//                    homeImg.setImageResource(R.mipmap.home_img_pressed);
//                    homeText.setTextColor(Color.parseColor("#4E4E4E"));
//
//                    keChengImg.setImageResource(R.mipmap.kecheng_img_normal);
//                    keChengText.setTextColor(Color.parseColor("#B1B1B1"));
//
//                    mySelfImg.setImageResource(R.mipmap.myself_img_normal);
//                    mySelfTxet.setTextColor(Color.parseColor("#B1B1B1"));
//                }
//                else if(position==1)
//                {
//                    homeImg.setImageResource(R.mipmap.home_img_normal);
//                    homeText.setTextColor(Color.parseColor("#B1B1B1"));
//
//                    keChengImg.setImageResource(R.mipmap.kecheng_img_press);
//                    keChengText.setTextColor(Color.parseColor("#4E4E4E"));
//
//                    mySelfImg.setImageResource(R.mipmap.myself_img_normal);
//                    mySelfTxet.setTextColor(Color.parseColor("#B1B1B1"));
//                }
//                else if (position==2)
//                {
//                    homeImg.setImageResource(R.mipmap.home_img_normal);
//                    homeText.setTextColor(Color.parseColor("#B1B1B1"));
//
//                    keChengImg.setImageResource(R.mipmap.kecheng_img_normal);
//                    keChengText.setTextColor(Color.parseColor("#B1B1B1"));
//
//                    mySelfImg.setImageResource(R.mipmap.myself_img_press);
//                    mySelfTxet.setTextColor(Color.parseColor("#4E4E4E"));
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_img:
                homeImg.setImageResource(R.mipmap.home_img_pressed);
                homeText.setTextColor(Color.parseColor("#4E4E4E"));

                keChengImg.setImageResource(R.mipmap.kecheng_img_normal);
                keChengText.setTextColor(Color.parseColor("#B1B1B1"));

                mySelfImg.setImageResource(R.mipmap.myself_img_normal);
                mySelfTxet.setTextColor(Color.parseColor("#B1B1B1"));
                // mViewPager.setCurrentItem(0);


                transaction = manager.beginTransaction();//每次commit都要开启新的事务
                if (mHomeFragment.isAdded()) {
                    if (currentFragment != mHomeFragment) {
                        transaction.show(mHomeFragment).hide(currentFragment).commit();
                        currentFragment = mHomeFragment;
                    }
                } else {
                    transaction.add(R.id.fragment_layout, mHomeFragment, "fragmentA").hide(currentFragment).commit();
                    //transaction.replace(R.id.fragment_layout,fragmentA);
                    currentFragment = mHomeFragment;
                }
            break;

            case R.id.home_text:
                homeImg.setImageResource(R.mipmap.home_img_pressed);
                homeText.setTextColor(Color.parseColor("#4E4E4E"));

                keChengImg.setImageResource(R.mipmap.kecheng_img_normal);
                keChengText.setTextColor(Color.parseColor("#B1B1B1"));

                mySelfImg.setImageResource(R.mipmap.myself_img_normal);
                mySelfTxet.setTextColor(Color.parseColor("#B1B1B1"));

                transaction = manager.beginTransaction();//每次commit都要开启新的事务
                if (mHomeFragment.isAdded()) {
                    if (currentFragment != mHomeFragment) {
                        transaction.show(mHomeFragment).hide(currentFragment).commit();
                        currentFragment = mHomeFragment;
                    }
                } else {
                    transaction.add(R.id.fragment_layout, mHomeFragment, "fragmentA").hide(currentFragment).commit();
                    //transaction.replace(R.id.fragment_layout,fragmentA);
                    currentFragment = mHomeFragment;
                }
                
                break;


            case R.id.kecheng_img:
                homeImg.setImageResource(R.mipmap.home_img_normal);
                homeText.setTextColor(Color.parseColor("#B1B1B1"));

                keChengImg.setImageResource(R.mipmap.kecheng_img_press);
                keChengText.setTextColor(Color.parseColor("#4E4E4E"));

                mySelfImg.setImageResource(R.mipmap.myself_img_normal);
                mySelfTxet.setTextColor(Color.parseColor("#B1B1B1"));

                transaction = manager.beginTransaction();//每次commit都要开启新的事务
                if (mKeChengFragment.isAdded()) {
                    if (currentFragment != mKeChengFragment) {
                        transaction.show(mKeChengFragment).hide(currentFragment).commit();
                        currentFragment = mKeChengFragment;
                    }
                } else {
                    transaction.add(R.id.fragment_layout, mKeChengFragment, "fragmentA").hide(currentFragment).commit();
                    //transaction.replace(R.id.fragment_layout,fragmentA);
                    currentFragment = mKeChengFragment;
                }
                
                break;
            case R.id.kecheng_text:
                homeImg.setImageResource(R.mipmap.home_img_normal);
                homeText.setTextColor(Color.parseColor("#B1B1B1"));

                keChengImg.setImageResource(R.mipmap.kecheng_img_press);
                keChengText.setTextColor(Color.parseColor("#4E4E4E"));

                mySelfImg.setImageResource(R.mipmap.myself_img_normal);
                mySelfTxet.setTextColor(Color.parseColor("#B1B1B1"));

                transaction = manager.beginTransaction();//每次commit都要开启新的事务
                if (mKeChengFragment.isAdded()) {
                    if (currentFragment !=mKeChengFragment) {
                        transaction.show(mKeChengFragment).hide(currentFragment).commit();
                        currentFragment = mKeChengFragment;
                    }
                } else {
                    transaction.add(R.id.fragment_layout, mKeChengFragment, "fragmentA").hide(currentFragment).commit();
                    //transaction.replace(R.id.fragment_layout,fragmentA);
                    currentFragment = mKeChengFragment;
                }
                
                break;

            
            
            case R.id.myself_img:


                SaveUser save=new SaveUser(HomeActivity.this);
                ConstantSet.user=(save.getData("userFile","user"));

                if(ConstantSet.user == null)
                {
//                    Toast.makeText(HomeActivity.this, "请登录...", Toast.LENGTH_SHORT).show();
                    ToastUtils.showTextToast("请登录", HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, LoginAndRegisterActivity.class));
//                    ((CourseActivity) mContext).finish();

                }
                else if (ConstantSet.user.getUid().equalsIgnoreCase("0")){
                    ToastUtils.showTextToast("请登录", HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, LoginAndRegisterActivity.class));

                }else {

                    homeImg.setImageResource(R.mipmap.home_img_normal);
                    homeText.setTextColor(Color.parseColor("#B1B1B1"));

                    keChengImg.setImageResource(R.mipmap.kecheng_img_normal);
                    keChengText.setTextColor(Color.parseColor("#B1B1B1"));

                    mySelfImg.setImageResource(R.mipmap.myself_img_press);
                    mySelfTxet.setTextColor(Color.parseColor("#4E4E4E"));
                    transaction = manager.beginTransaction();//每次commit都要开启新的事务
                    if (mMyselfFragment.isAdded()) {
                        if (currentFragment != mMyselfFragment) {
                            transaction.show(mMyselfFragment).hide(currentFragment).commit();
                            currentFragment = mMyselfFragment;
                        }
                    } else {
                        transaction.add(R.id.fragment_layout, mMyselfFragment, "fragmentA").hide(currentFragment).commit();
                        //transaction.replace(R.id.fragment_layout,fragmentA);
                        currentFragment = mMyselfFragment;
                    }
                }
                break;
            case R.id.myself_text:
                SaveUser save2=new SaveUser(HomeActivity.this);
                ConstantSet.user=(save2.getData("userFile","user"));
                if(ConstantSet.user == null)
                {
                    ToastUtils.showTextToast("请登录", HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, LoginAndRegisterActivity.class));
//                    ((CourseActivity) mContext).finish();

                }
                else if (ConstantSet.user.getUid().equalsIgnoreCase("0")){
                    ToastUtils.showTextToast("请登录", HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, LoginAndRegisterActivity.class));

                }else {
                    homeImg.setImageResource(R.mipmap.home_img_normal);
                    homeText.setTextColor(Color.parseColor("#B1B1B1"));

                    keChengImg.setImageResource(R.mipmap.kecheng_img_normal);
                    keChengText.setTextColor(Color.parseColor("#B1B1B1"));

                    mySelfImg.setImageResource(R.mipmap.myself_img_press);
                    mySelfTxet.setTextColor(Color.parseColor("#4E4E4E"));

                    transaction = manager.beginTransaction();//每次commit都要开启新的事务
                    if (mMyselfFragment.isAdded()) {
                        if (currentFragment != mMyselfFragment) {
                            transaction.show(mMyselfFragment).hide(currentFragment).commit();
                            currentFragment = mMyselfFragment;
                        }
                    } else {
                        transaction.add(R.id.fragment_layout, mMyselfFragment, "fragmentA").hide(currentFragment).commit();
                        //transaction.replace(R.id.fragment_layout,fragmentA);
                        currentFragment = mMyselfFragment;
                    }
                }
                
                break;
        }
    }

    public void getUserInfo()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getuserinfo?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                try {

                   // Toast.makeText(HomeActivity.this,response,Toast.LENGTH_SHORT).show();
                    
                   // System.out.print("asdasdasd    "+response);
                    Gson gson = new Gson();
                    ResultUser confiMap=  gson.fromJson(response, new TypeToken<ResultUser>() {
                    }.getType());

                    if (confiMap.getStatus().equalsIgnoreCase("1")) {

                        ConstantSet.user = confiMap.getData();
                        ConstantSet.user.setAvatar(ConstantSet.user.getAvatar());
                        SaveUser save = new SaveUser(HomeActivity.this);
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    @Override
    public void onStop() {
        super.onStop();
        new SharedPreferencesHelper(this).saveData("history", "historyKey", MyApplication.getHistoryLists());
    }




    public void getMyClass()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getmycourse?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                try {
                    
                    
                   // showShortToast(response);

                    Gson gson = new Gson();
                   ResultMyClass result=  gson.fromJson(response, new TypeToken<ResultMyClass>() {
                    }.getType());
                    if (result.getStatus().equalsIgnoreCase("1")) {
                        ConstantSet.myClassList = result.getData();
                    }

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    //判断是否签到过
    public void getSignData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"ding/judgeding?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                try {

                    //Toast.makeText(StartupActivity.this,response+"222",Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
                    }.getType());
                    if (resultUser.getStatus() == 1) {

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String str = formatter.format(new Date());
                        if (resultUser.getData() != null) {
                            ConstantSet.jifen = resultUser.getData().getIntegral_value();
                        }

                        if (resultUser.getMessage().equals("今天未签到")) {
                            ConstantSet.sign = false;
                            SignDialog dialog = new SignDialog(HomeActivity.this);
                            Window window = dialog.getWindow();
                            window.setGravity(Gravity.CENTER);
                            dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                            dialog.show();
                        }
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

}
