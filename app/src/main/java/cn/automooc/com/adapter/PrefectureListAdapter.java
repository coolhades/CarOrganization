package cn.automooc.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.automooc.com.R;
import cn.automooc.com.bean.PrefectureListBean;
import cn.automooc.com.utils.LoadImgUtils;

/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class PrefectureListAdapter extends BaseAdapter{

    List<PrefectureListBean.DataBean.ListBean> lists;
    Context mContext;

    public PrefectureListAdapter(List<PrefectureListBean.DataBean.ListBean> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            view=View.inflate(mContext, R.layout.prefecture_mingshi_listview_item,null);
            viewHolder.img= (ImageView) view.findViewById(R.id.head_img);
            viewHolder.titleText= (TextView) view.findViewById(R.id.name);
            viewHolder.course_count = (TextView) view.findViewById(R.id.course_count);
            viewHolder.content_tv = (TextView) view.findViewById(R.id.content_tv);
            viewHolder.teacher_name = (TextView) view.findViewById(R.id.teacher_name);
//            viewHolder.tip1= (TextView) view.findViewById(R.id.tip1);
//            viewHolder.tip2= (TextView) view.findViewById(R.id.tip2);
            viewHolder.nextImg= (ImageView) view.findViewById(R.id.next_bt);
            
            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
                    
        }

        LoadImgUtils.setImage(mContext,lists.get(i).getPgc_logo(),viewHolder.img);
        viewHolder.titleText.setText(lists.get(i).getPgc_name());
        viewHolder.course_count.setText("课程"+lists.get(i).getCourse_count()+"门");
        viewHolder.content_tv.setText(lists.get(i).getPgc_desc());
        viewHolder.teacher_name.setText("老师"+lists.get(i).getTeacher_count()+"人");
//        viewHolder.tip1.setText("老师"+lists.get(i).getTeacher_count()+"人");
//        viewHolder.tip2.setText("课程"+lists.get(i).getCourse_count()+"门");


        viewHolder.nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        
        
        
        return view;
    }
    
    
    class ViewHolder
    {
        ImageView img;
        TextView titleText;
        TextView course_count;
        TextView content_tv;
        TextView teacher_name;

//        TextView tip1;
//        TextView tip2;
        ImageView nextImg;
    }
    
}
