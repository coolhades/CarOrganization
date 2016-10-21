package cn.automooc.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.automooc.com.R;
import cn.automooc.com.bean.Question;
import cn.automooc.com.ui.CourseActivity;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.LoadImgUtils;
import cn.automooc.com.widget.CircularImage;

public class MyQuestionListViewAdapter extends BaseAdapter{

	
	List<Question> list;
	Context mContext;
	
	
	
	public MyQuestionListViewAdapter(List<Question> list, Context mContext) {
		super();
		this.list = list;
		this.mContext = mContext;
	}

	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder viewHolder=null;
		if(convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView=View.inflate(mContext, R.layout.my_question_item, null);
			viewHolder.headImg= (CircularImage) convertView.findViewById(R.id.head_img);
			viewHolder.titleText= (TextView) convertView.findViewById(R.id.title_text);
			viewHolder.dateText= (TextView) convertView.findViewById(R.id.date_text);
			viewHolder.contentText= (TextView) convertView.findViewById(R.id.content_text);
			viewHolder.nextBar= (LinearLayout) convertView.findViewById(R.id.next_bar);
			viewHolder.chakanTextView= (TextView) convertView.findViewById(R.id.chakan_text);
			viewHolder.quest_item = (LinearLayout) convertView.findViewById(R.id.quest_item);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}

		LoadImgUtils.setImage(mContext,list.get(position).getUserInfo().getAvatar(),viewHolder.headImg);
		viewHolder.titleText.setText(list.get(position).getUserInfo().getNickname());
		viewHolder.dateText.setText(list.get(position).getCdate());
		viewHolder.contentText.setText(list.get(position).getContent());
		if(!(list.get(position).getCount().equals("0")))
		{
			viewHolder.chakanTextView.setText("查看对话（"+list.get(position).getCount()+"）");
		}
		else
		{
			viewHolder.nextBar.setVisibility(View.GONE);
		}

		viewHolder.quest_item.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ConstantSet.course_id = list.get(position).getImpower_id();
				Intent i  = new Intent(mContext, CourseActivity.class);
//				i.putExtra("impower_id", list.get(position).getImpower_id());
				mContext.startActivity(i);
			}
		});

		return convertView;
	}

	
	class ViewHolder
	{


		CircularImage headImg;
		TextView titleText;
		TextView dateText;
		TextView contentText;
		LinearLayout nextBar;
		TextView chakanTextView;
		LinearLayout quest_item;
	}
	
}
