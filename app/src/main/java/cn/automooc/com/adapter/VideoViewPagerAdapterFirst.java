package cn.automooc.com.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import cn.automooc.com.utils.ConstantSet;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/10.
 */
public class VideoViewPagerAdapterFirst extends PagerAdapter {
    
    List<View> lists;
    Context mContext;

    public VideoViewPagerAdapterFirst(List<View> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (ConstantSet.confiMap.get("App.Switch.Course.Evaluate.Show").equals("0"))
        {
            return 2;
        }
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(lists.get(position));
        
        return lists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(lists.get(position));
    }
}
