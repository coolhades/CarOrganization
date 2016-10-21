package cn.automooc.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.automooc.com.R;
import cn.automooc.com.bean.SearchLesson;
import cn.automooc.com.ui.CourseActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.LoadImgUtils;

/**
 * Created by jiuzheyange on 2016/8/9.
 */
public class TypeListAdapter extends ListBaseAdapter{
    
    Context mContext;
    List<SearchLesson> lists;
    
    

    public TypeListAdapter(Context mContext, List<SearchLesson> lists) {
        this.mContext = mContext;
        this.lists=lists;
    }


    @Override
    public int getItemCount() {
        Log.i("TAG-ListSize", "="+lists.size());
        return lists.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.type_listview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        //强转一下拉
        ((MyViewHolder) holder).keshiText.setText(lists.get(position).getNum_hour()+"课时");
        ((MyViewHolder) holder).renqiText.setText("人气 "+lists.get(position).getNum_visit());
        if(lists.get(position).getPrice().equals("0"))
        {
            ((MyViewHolder) holder).priceText.setText("免费");
        }else {
            ((MyViewHolder) holder).priceText.setText("￥" + lists.get(position).getPrice());
        }
        LoadImgUtils.setImage(mContext,lists.get(position).getCourse_album(),((MyViewHolder) holder).img);

        if (ConstantSet.confiMap != null) {
            if (ConstantSet.confiMap.get("App.Switch.Course.MaskInfo").equals("1")) {
                ((MyViewHolder) holder).title.setText(lists.get(position).getCourse_name());
                ((MyViewHolder) holder).teachername_text.setText(lists.get(position).getTeacher_name());
            }
        }

        ((MyViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConstantSet.course_id = lists.get(position).getCourse_id();
                ConstantSet.videoTitle = ConstantSet.keyWord;
                mContext.startActivity(new Intent(mContext, CourseActivity.class));

//                if (!(isAdd(lists.get(position).getCourse_id()))) {
//                    ConstantSet.course_id = lists.get(position).getCourse_id();
//                    ConstantSet.videoTitle = ConstantSet.keyWord;
//
//                    mContext.startActivity(new Intent(mContext, CourseActivity.class));
//                }
//                else if (isAdd(lists.get(position).getCourse_id())) {
//                    ConstantSet.course_id = lists.get(position).getCourse_id();
//                    ConstantSet.videoTitle = ConstantSet.keyWord;
//
//                    mContext.startActivity(new Intent(mContext, VideoActivity.class));
//                }
                
            }
        });
        
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView renqiText;
        TextView keshiText;
        TextView priceText;
        TextView teachername_text;


        TextView title;
        TextView teacherAndDate;

        public MyViewHolder(View itemView) {
            super(itemView);

            img= (ImageView) itemView.findViewById(R.id.img);
            renqiText= (TextView) itemView.findViewById(R.id.renqi_text);
            keshiText= (TextView) itemView.findViewById(R.id.keshi_text);
            priceText= (TextView) itemView.findViewById(R.id.price_text);

            title= (TextView) itemView.findViewById(R.id.title_text);
            teacherAndDate= (TextView) itemView.findViewById(R.id.teacher_and_date);
            teachername_text = (TextView) itemView.findViewById(R.id.teachername_text);

        }
    }


    public boolean isAdd(String course_id) {
        for (int i = 0; i < ConstantSet.myClassList.size(); i++) {
            if (ConstantSet.myClassList.get(i).getImpower_id().equals(course_id)) {
                return true;
            }
        }

        return false;
    }
}
