package cn.automooc.com.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.automooc.com.R;
import cn.automooc.com.adapter.HomeTopPagerAdapter;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.LoadImgUtils;
import cn.automooc.com.view.OrganIntroduceView;
import cn.automooc.com.view.PrefectureDetailCourseView;
import cn.automooc.com.view.PrefectureTeacherView;

//专区页面 list 跳转
public class PrefectureDetailActivity extends AppCompatActivity {

    
    //这里公用Type适配器
    
    ViewPager viewPager;
    List<View> lists;
    
    HomeTopPagerAdapter adapter;
    
    TextView keDanText;
    TextView jianJieText;
    TextView teacher_text;
    ImageView keDanImg;
    ImageView jianJieImg;
    ImageView teacher_img;
    
    TextView nameText;
    ImageView headImg;
    ImageView back_iv;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanqu);
        
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        back_iv = (ImageView) findViewById(R.id.back_iv);
        viewPager= (ViewPager) findViewById(R.id.view_pager);
        
        keDanText= (TextView) findViewById(R.id.kedan_text);
        jianJieText= (TextView) findViewById(R.id.jianjie_text);
        teacher_text = (TextView) findViewById(R.id.teacher_text);

        keDanImg= (ImageView) findViewById(R.id.kedan_img);
        jianJieImg= (ImageView) findViewById(R.id.jianjie_img);
        teacher_img = (ImageView) findViewById(R.id.teacher_img);

        nameText= (TextView) findViewById(R.id.name_text);
        nameText.setText(ConstantSet.teacher_name);
        
        headImg= (ImageView) findViewById(R.id.head_img);

        LoadImgUtils.setImage(this,ConstantSet.teacher_url,headImg);
    }

    private void initData() {
        
        lists=new ArrayList<View>();
        lists.add(new PrefectureDetailCourseView(this).getView());
        lists.add(new PrefectureTeacherView(this).getView());
        lists.add(new OrganIntroduceView(this).getView());
        
        adapter=new HomeTopPagerAdapter(lists,this);
        
        viewPager.setAdapter(adapter);
    }

    private void initEvent() {
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefectureDetailActivity.this.finish();
            }
        });
        
        keDanText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keDanText.setTextColor(Color.parseColor("#333333"));
                jianJieText.setTextColor(Color.parseColor("#C4C4C4"));
                teacher_text.setTextColor(Color.parseColor("#C4C4C4"));

                keDanImg.setImageResource(R.mipmap.heixian_img);
                jianJieImg.setImageResource(R.mipmap.touming_img);
                teacher_img.setImageResource(R.mipmap.touming_img);
                viewPager.setCurrentItem(0);
                
            }
        });

        teacher_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keDanText.setTextColor(Color.parseColor("#C4C4C4"));
                jianJieText.setTextColor(Color.parseColor("#C4C4C4"));
                teacher_text.setTextColor(Color.parseColor("#333333"));

                keDanImg.setImageResource(R.mipmap.touming_img);
                jianJieImg.setImageResource(R.mipmap.touming_img);
                teacher_img.setImageResource(R.mipmap.heixian_img);
                viewPager.setCurrentItem(1);
            }
        });
        
        
        jianJieText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keDanText.setTextColor(Color.parseColor("#C4C4C4"));
                jianJieText.setTextColor(Color.parseColor("#333333"));
                teacher_text.setTextColor(Color.parseColor("#C4C4C4"));

                keDanImg.setImageResource(R.mipmap.touming_img);
                jianJieImg.setImageResource(R.mipmap.heixian_img);
                teacher_img.setImageResource(R.mipmap.touming_img);
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
                    keDanText.setTextColor(Color.parseColor("#333333"));
                    jianJieText.setTextColor(Color.parseColor("#C4C4C4"));
                    teacher_text.setTextColor(Color.parseColor("#C4C4C4"));

                    keDanImg.setImageResource(R.mipmap.heixian_img);
                    jianJieImg.setImageResource(R.mipmap.touming_img);
                    teacher_img.setImageResource(R.mipmap.touming_img);
                }
                else if (position == 1){
                    keDanText.setTextColor(Color.parseColor("#C4C4C4"));
                    jianJieText.setTextColor(Color.parseColor("#C4C4C4"));
                    teacher_text.setTextColor(Color.parseColor("#333333"));

                    keDanImg.setImageResource(R.mipmap.touming_img);
                    jianJieImg.setImageResource(R.mipmap.touming_img);
                    teacher_img.setImageResource(R.mipmap.heixian_img);
                }
                else if(position==2)
                {
                    keDanText.setTextColor(Color.parseColor("#C4C4C4"));
                    jianJieText.setTextColor(Color.parseColor("#333333"));
                    teacher_text.setTextColor(Color.parseColor("#C4C4C4"));

                    keDanImg.setImageResource(R.mipmap.touming_img);
                    jianJieImg.setImageResource(R.mipmap.heixian_img);
                    teacher_img.setImageResource(R.mipmap.touming_img);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
