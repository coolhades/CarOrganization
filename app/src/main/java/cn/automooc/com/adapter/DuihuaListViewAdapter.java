package cn.automooc.com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.automooc.com.R;
import cn.automooc.com.bean.QuestionListBean;
import cn.automooc.com.utils.LoadImgUtils;

public class DuihuaListViewAdapter extends BaseAdapter{


	List<QuestionListBean.DataBean> list;
	Context mContext;



	public DuihuaListViewAdapter(List<QuestionListBean.DataBean> list, Context mContext) {
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
			convertView=View.inflate(mContext, R.layout.duihua_listview_item, null);

			viewHolder.headImg= (ImageView) convertView.findViewById(R.id.head_img);
			viewHolder.userNick= (TextView) convertView.findViewById(R.id.user_nick);
			viewHolder.dateText= (TextView) convertView.findViewById(R.id.date);
			viewHolder.contentText= (TextView) convertView.findViewById(R.id.content);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}

		LoadImgUtils.setImage(mContext,list.get(position).getReplyUserInfo().getAvatar(),viewHolder.headImg);
		viewHolder.userNick.setText(list.get(position).getReplyUserInfo().getNickname());
		viewHolder.dateText.setText(list.get(position).getCdate());
		viewHolder.contentText.setText(list.get(position).getContent());
		Log.i("TAGTAGTAG", "执行到了");
		return convertView;
	}


	class ViewHolder
	{

		ImageView headImg;
		TextView userNick;
		TextView dateText;
		TextView contentText;
	}

}
