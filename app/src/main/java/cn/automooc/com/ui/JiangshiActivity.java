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
import cn.automooc.com.view.JiangshiView1;
import cn.automooc.com.view.JiangshiView2;

//名师页面 list 跳转
public class JiangshiActivity extends AppCompatActivity {

    
    //这里公用Type适配器
    
    ViewPager viewPager;
    List<View> lists;
    
    HomeTopPagerAdapter adapter;
    
    TextView keDanText;
    TextView jianJieText;
    ImageView keDanImg;
    ImageView jianJieImg;
    ImageView back_iv;
    
    TextView nameText;
    ImageView headImg;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiangshi);
        
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        viewPager= (ViewPager) findViewById(R.id.view_pager);
        
        keDanText= (TextView) findViewById(R.id.kedan_text);
        jianJieText= (TextView) findViewById(R.id.jianjie_text);
        
        keDanImg= (ImageView) findViewById(R.id.kedan_img);
        jianJieImg= (ImageView) findViewById(R.id.jianjie_img);

        nameText= (TextView) findViewById(R.id.name_text);
        nameText.setText(ConstantSet.teacher_name);
        
        headImg= (ImageView) findViewById(R.id.head_img);

        LoadImgUtils.setImage(this,ConstantSet.teacher_url,headImg);

        back_iv = (ImageView) findViewById(R.id.back_iv);
    }

    private void initData() {
        
        lists=new ArrayList<View>();
        lists.add(new JiangshiView1(this).getView());
        lists.add(new JiangshiView2(this).getView());
        
        adapter=new HomeTopPagerAdapter(lists,this);
        
        viewPager.setAdapter(adapter);
    }

    private void initEvent() {
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JiangshiActivity.this.finish();
            }
        });
        
        keDanText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                
                keDanText.setTextColor(Color.parseColor("#333333"));
                jianJieText.setTextColor(Color.parseColor("#C4C4C4"));
                
                keDanImg.setImageResource(R.mipmap.heixian_img);
                jianJieImg.setImageResource(R.mipmap.touming_img);
                viewPager.setCurrentItem(0);
                
            }
        });
        
        
        jianJieText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keDanText.setTextColor(Color.parseColor("#C4C4C4"));
                jianJieText.setTextColor(Color.parseColor("#333333"));

                keDanImg.setImageResource(R.mipmap.touming_img);
                jianJieImg.setImageResource(R.mipmap.heixian_img);
                viewPager.setCurrentItem(1);
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

                    keDanImg.setImageResource(R.mipmap.heixian_img);
                    jianJieImg.setImageResource(R.mipmap.touming_img);
                }
                else if(position==1)
                {
                    keDanText.setTextColor(Color.parseColor("#C4C4C4"));
                    jianJieText.setTextColor(Color.parseColor("#333333"));

                    keDanImg.setImageResource(R.mipmap.touming_img);
                    jianJieImg.setImageResource(R.mipmap.heixian_img);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
