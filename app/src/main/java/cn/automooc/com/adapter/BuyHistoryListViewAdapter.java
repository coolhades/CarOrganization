package cn.automooc.com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.automooc.com.R;
import cn.automooc.com.bean.MyOrderListBean;
import cn.automooc.com.utils.LoadImgUtils;

public class BuyHistoryListViewAdapter extends BaseAdapter{

	
	List<MyOrderListBean.DataBean.ListBean> list;
	Context mContext;
	
	
	
	public BuyHistoryListViewAdapter(List<MyOrderListBean.DataBean.ListBean> list, Context mContext) {
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
		
		final ViewHolder viewHolder;
		if(convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView=View.inflate(mContext, R.layout.buy_history_listview_item, null);
			viewHolder.item_img = (ImageView) convertView.findViewById(R.id.item_img);
			viewHolder.item_title = (TextView) convertView.findViewById(R.id.item_title);
			viewHolder.item_content = (TextView) convertView.findViewById(R.id.item_content);
			viewHolder.money = (TextView) convertView.findViewById(R.id.money);
			viewHolder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
			viewHolder.ispaid = (TextView) convertView.findViewById(R.id.ispaid);

			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		LoadImgUtils.setImage(mContext, list.get(position).getCourse_album(), viewHolder.item_img);
		viewHolder.item_title.setText(list.get(position).getCourse_name());
		viewHolder.item_content.setText(list.get(position).getClass_name());
		viewHolder.money.setText("￥: "+ list.get(position).getMoney_pay());
		viewHolder.time_tv.setText(list.get(position).getCreatetime());
		if (list.get(position).getStatus().equalsIgnoreCase("1")){
			//已支付
			viewHolder.ispaid.setText("已支付");
			viewHolder.ispaid.setTextColor(Color.parseColor("#FF3C00"));
		}else {
			viewHolder.ispaid.setText("未支付");
			viewHolder.ispaid.setTextColor(Color.parseColor("#969696"));
		}


		
		return convertView;
	}

	
	class ViewHolder
	{
		ImageView item_img;
		TextView item_title;
		TextView item_content;
		TextView money;
		TextView time_tv;
		TextView ispaid;
	}
	
}
