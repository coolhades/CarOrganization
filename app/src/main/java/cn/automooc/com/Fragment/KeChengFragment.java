package cn.automooc.com.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.automooc.com.BaseFragment;
import cn.automooc.com.R;
import cn.automooc.com.adapter.HomeTopPagerAdapter2;
import cn.automooc.com.ui.SearchActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.view.KechengView;
import cn.automooc.com.view.PrefectureList_View;
import cn.automooc.com.view.TeacherView;

/**
 * Created by jiuzheyange on 2016/8/8.
 */
public class KeChengFragment extends BaseFragment {

    View view;

    TextView keChengText;
    TextView mingShiText;
    TextView zhuanquText;
    
    ImageView kechengImg;
    ImageView mingshiImg;
    ImageView zhuanquImg;
    
    ViewPager viewPager;
    
    List<View> lists;
    
    HomeTopPagerAdapter2 adapter;
    
    
    ImageView searchBt;
    
    LinearLayout kechengBar;
    LinearLayout kechengImgBar;
    View viewBg;
    
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    protected void initView() {
        view=View.inflate(getActivity(), R.layout.kecheng_fragment,null);
     
        keChengText= (TextView) view.findViewById(R.id.kecheng_text);
        mingShiText= (TextView) view.findViewById(R.id.mingshi_text);
        zhuanquText= (TextView) view.findViewById(R.id.zhuanqu_text);
        
        kechengImg= (ImageView) view.findViewById(R.id.kecheng_img);
        mingshiImg= (ImageView) view.findViewById(R.id.mingshi_img);
        zhuanquImg= (ImageView) view.findViewById(R.id.zhuanqu_img);

        viewPager= (ViewPager) view.findViewById(R.id.kecheng_viewpager);

        searchBt= (ImageView) view.findViewById(R.id.search_bt);
        
        kechengBar= (LinearLayout) view.findViewById(R.id.kecheng_bar);
        kechengImgBar= (LinearLayout) view.findViewById(R.id.kecheng_img_bar);
        viewBg=view.findViewById(R.id.xian_bg);
        if(ConstantSet.confiMap!=null) {
            if (ConstantSet.confiMap.get("App.Switch.Teacher.Show").equals("0")) {
                kechengBar.setVisibility(View.GONE);
                kechengImgBar.setVisibility(View.GONE);
                viewBg.setVisibility(View.GONE);
            }
        }
        
    }

    @Override
    protected void initData() {

        lists=new ArrayList<View>();
        lists.add(new KechengView(getActivity()).getView());
        if(ConstantSet.confiMap!=null) {
            if (ConstantSet.confiMap.get("App.Switch.Teacher.Show").equals("1")) {
                lists.add(new TeacherView(getActivity()).getView());
            }
        }
        lists.add(new PrefectureList_View(getActivity()).getView());


        Gson gson = new Gson();
        Log.i("TAG-config", gson.toJson(ConstantSet.confiMap));

        adapter=new HomeTopPagerAdapter2(lists,getActivity());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        
    }

    @Override
    protected void initEvent() {
        
        
        keChengText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                kechengImg.setImageResource(R.mipmap.heixian_img);
                keChengText.setTextColor(Color.parseColor("#333333"));
                mingshiImg.setImageResource(R.mipmap.touming_img);
                mingShiText.setTextColor(Color.parseColor("#BCBCBC"));
                zhuanquImg.setImageResource(R.mipmap.touming_img);
                zhuanquText.setTextColor(Color.parseColor("#BCBCBC"));
                
                viewPager.setCurrentItem(0);
                
            }
        });




        mingShiText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kechengImg.setImageResource(R.mipmap.touming_img);
                keChengText.setTextColor(Color.parseColor("#BCBCBC"));
                mingshiImg.setImageResource(R.mipmap.heixian_img);
                mingShiText.setTextColor(Color.parseColor("#333333"));
                zhuanquImg.setImageResource(R.mipmap.touming_img);
                zhuanquText.setTextColor(Color.parseColor("#BCBCBC"));

                viewPager.setCurrentItem(1);
            }
        });


        zhuanquText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kechengImg.setImageResource(R.mipmap.touming_img);
                keChengText.setTextColor(Color.parseColor("#BCBCBC"));
                mingshiImg.setImageResource(R.mipmap.touming_img);
                mingShiText.setTextColor(Color.parseColor("#BCBCBC"));
                zhuanquImg.setImageResource(R.mipmap.heixian_img);
                zhuanquText.setTextColor(Color.parseColor("#333333"));

                viewPager.setCurrentItem(2);
            }
        });


     /*   listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });*/
        
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {

                if(position==0)
                {
                    kechengImg.setImageResource(R.mipmap.heixian_img);
                    keChengText.setTextColor(Color.parseColor("#333333"));
                    mingshiImg.setImageResource(R.mipmap.touming_img);
                    mingShiText.setTextColor(Color.parseColor("#BCBCBC"));
                    zhuanquImg.setImageResource(R.mipmap.touming_img);
                    zhuanquText.setTextColor(Color.parseColor("#BCBCBC"));
                }
                
                else if(position==1)
                {
                    kechengImg.setImageResource(R.mipmap.touming_img);
                    keChengText.setTextColor(Color.parseColor("#BCBCBC"));
                    mingshiImg.setImageResource(R.mipmap.heixian_img);
                    mingShiText.setTextColor(Color.parseColor("#333333"));
                    zhuanquImg.setImageResource(R.mipmap.touming_img);
                    zhuanquText.setTextColor(Color.parseColor("#BCBCBC"));
                }else if (position == 2){
                    kechengImg.setImageResource(R.mipmap.touming_img);
                    keChengText.setTextColor(Color.parseColor("#BCBCBC"));
                    mingshiImg.setImageResource(R.mipmap.touming_img);
                    mingShiText.setTextColor(Color.parseColor("#BCBCBC"));
                    zhuanquImg.setImageResource(R.mipmap.heixian_img);
                    zhuanquText.setTextColor(Color.parseColor("#333333"));
                }
                
                
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }
    
}
