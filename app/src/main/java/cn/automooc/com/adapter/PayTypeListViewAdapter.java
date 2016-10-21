package cn.automooc.com.adapter;

/**
 * Created by Hades on 16/9/22.
 */
//public class PayTypeListViewAdapter extends BaseAdapter{
//
//        private Context context;
//
//        List<PayTypeBean.DataBean> beans;
//
//
//        class ViewHolder {
//            ImageView icon;
//            TextView tvName;
//            CheckBox cb;
//            LinearLayout LL;
//        }
//
//        public PayTypeListViewAdapter(Context context, List<PayTypeBean.DataBean> beans) {
//            // TODO Auto-generated constructor stub
//            this.beans = beans;
//            this.context = context;
//
//        }
//
//
//
//        @Override
//        public int getCount() {
//            // TODO Auto-generated method stub
//            return beans.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            // TODO Auto-generated method stub
//            return beans.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            // TODO Auto-generated method stub
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            // TODO Auto-generated method stub
//            // 页面
//            Log.v("MyListViewBase", "getView " + position + " " + convertView);
//            ViewHolder holder = null;
//
//            LayoutInflater inflater = LayoutInflater.from(context);
//            if (convertView == null) {
//                convertView = inflater.inflate(
//                        R.layout.paytype_item, null);
//                holder = new ViewHolder();
//                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
//                holder.cb = (CheckBox) convertView.findViewById(R.id.checkbox);
//                holder.tvName = (TextView) convertView.findViewById(R.id.typename);
//                holder.LL = (LinearLayout) convertView.findViewById(R.id.linear_layout_up);
//                convertView.setTag(holder);
//            } else {
//                // 取出holder
//                holder = (ViewHolder) convertView.getTag();
//            }
//            holder.tvName.setText(beans.get(position).getTitle());
//
//            return convertView;
//        }
//
//}
