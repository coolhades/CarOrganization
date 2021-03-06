package cn.automooc.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.automooc.com.R;
import cn.automooc.com.bean.GongGaoBean;
import cn.automooc.com.ui.GongGaoDetailActivity;

public class GongGaoListViewAdapter extends BaseAdapter{


	List<GongGaoBean.DataBean> list;
	Context mContext;



	public GongGaoListViewAdapter(List<GongGaoBean.DataBean> list, Context mContext) {
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
			convertView=View.inflate(mContext, R.layout.gonggao_listview_item, null);

//			viewHolder.time= (TextView) convertView.findViewById(R.id.time_gonggao);
//			viewHolder.year= (TextView) convertView.findViewById(R.id.year_gonggao);
			viewHolder.itemTitle= (TextView) convertView.findViewById(R.id.title_gonggao);
			viewHolder.item_content= (TextView) convertView.findViewById(R.id.content_gonggao);
			viewHolder.gonggao_ly = (LinearLayout) convertView.findViewById(R.id.gonggao_ly);
			viewHolder.date_create = (TextView) convertView.findViewById(R.id.date_create);

			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		//event
//		viewHolder.time.setText("");
//		viewHolder.year.setText("");
		viewHolder.itemTitle.setText(list.get(position).getTitle());
		viewHolder.item_content.setText(list.get(position).getContent());
		viewHolder.date_create.setText(list.get(position).getDate_create());

		viewHolder.gonggao_ly.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转查看具体公告
				//传Bundle
				Bundle bundle = new Bundle();
				bundle.putString("title", list.get(position).getTitle());
				bundle.putString("content", list.get(position).getContent());
				bundle.putString("data", list.get(position).getDate_create());
				Intent i = new Intent(mContext,GongGaoDetailActivity.class);
				i.putExtras(bundle);
				mContext.startActivity(i);

			}
		});



		
		return convertView;
	}

	
	class ViewHolder
	{
//		TextView time;
//		TextView year;
		TextView itemTitle;
		TextView item_content;
		TextView date_create;
		LinearLayout gonggao_ly;

	}
	
}
