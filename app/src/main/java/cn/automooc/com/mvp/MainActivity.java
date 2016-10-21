package cn.automooc.com.mvp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hades.libam.ui.activity.BaseActivity;
import com.hades.libam.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import cn.automooc.com.Fragment.HomeFragment;
import cn.automooc.com.Fragment.KeChengFragment;
import cn.automooc.com.Fragment.MyselfFragment;
import cn.automooc.com.R;
import cn.automooc.com.mvp.presenter.MainPresenter;
import cn.automooc.com.mvp.view.IMainView;
import cn.automooc.com.ui.GuideActivity;
import cn.automooc.com.ui.LoginAndRegisterActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.SaveUser;
import cn.automooc.com.utils.ToastUtils;

/**
 * Created by Hades on 16/10/17.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView,View.OnClickListener {


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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected MainPresenter onLoadPresenter() {
        return new MainPresenter();
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        if (!SPUtils.getValue(this, "Login", "isFirstLogin", false)){
            Intent i = new Intent(this, GuideActivity.class);
            startActivity(i);
            this.finish();
        }else {
            initview();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            if (savedInstanceState == null) {
                transaction.add(R.id.fragment_layout, mHomeFragment, "fragmentA");
                //transaction.replace(R.id.fragment_layout,fragmentA);
                transaction.commit();
                currentFragment = mHomeFragment;
            }
        }

    }

    private void initview() {
        lists=new ArrayList<Fragment>();

        mHomeFragment=new HomeFragment();
        mKeChengFragment=new KeChengFragment();
        mMyselfFragment=new MyselfFragment();
        lists.add(mHomeFragment);
        lists.add(mKeChengFragment);
        lists.add(mMyselfFragment);



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

    }

    @Override
    protected void initData() {
        mPresenter.getUserInfo();
        mPresenter.getCourseInfo();

    }

    @Override
    protected void initEvent() {

    }

    //加载数据回调
    @Override
    public void onLodeData(Object data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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


                SaveUser save=new SaveUser(MainActivity.this);
                ConstantSet.user=(save.getData("userFile","user"));

                if(ConstantSet.user == null)
                {
//                    Toast.makeText(HomeActivity.this, "请登录...", Toast.LENGTH_SHORT).show();
                    ToastUtils.showTextToast("请登录", MainActivity.this);
                    startActivity(new Intent(MainActivity.this, LoginAndRegisterActivity.class));
//                    ((CourseActivity) mContext).finish();

                }
                else if (ConstantSet.user.getUid().equalsIgnoreCase("0")){
                    ToastUtils.showTextToast("请登录", MainActivity.this);
                    startActivity(new Intent(MainActivity.this, LoginAndRegisterActivity.class));

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
                SaveUser save2=new SaveUser(MainActivity.this);
                ConstantSet.user=(save2.getData("userFile","user"));
                if(ConstantSet.user == null)
                {
                    ToastUtils.showTextToast("请登录", MainActivity.this);
                    startActivity(new Intent(MainActivity.this, LoginAndRegisterActivity.class));
//                    ((CourseActivity) mContext).finish();

                }
                else if (ConstantSet.user.getUid().equalsIgnoreCase("0")){
                    ToastUtils.showTextToast("请登录", MainActivity.this);
                    startActivity(new Intent(MainActivity.this, LoginAndRegisterActivity.class));

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
}
