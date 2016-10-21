package cn.automooc.com.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pingplusplus.android.PaymentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.BaseActivity;
import cn.automooc.com.R;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.ClassArrBean;
import cn.automooc.com.bean.OrderBean;
import cn.automooc.com.bean.PayTypeBean;
import cn.automooc.com.bean.TargetIdBean;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.LoadImgUtils;
import cn.automooc.com.utils.Md5Utils;

/**
 * Created by Hades on 16/9/21.
 */
public class PayActivity extends BaseActivity {
    PayTypeBean mPayTypeBean;
    ListView paytypeshow_tv;
    PayTypeListViewAdapter adapter;

    List<PayTypeBean.DataBean> list;

    TargetIdBean bean;
    private static final int REQUEST_CODE_PAYMENT = 1;
    ImageView back;

    ClassArrBean resultClassBean;
    int classindex;

    TextView course_title;
    TextView teacher_name;
    TextView price_tv;
    TextView go_pay;
    private int selectPosition;
    private String channel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_layout);

        try {
            if (!getIntent().getStringExtra("class").isEmpty() ){
                Gson gson = new Gson();
                classindex = getIntent().getIntExtra("class_index", 500);
//                Toast.makeText(PayActivity.this, getIntent().getStringExtra("teacher_name"), Toast.LENGTH_SHORT).show();
                resultClassBean = gson.fromJson(getIntent().getStringExtra("class"), ClassArrBean.class);
            }
        }catch (Exception e){

        }


        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        paytypeshow_tv = (ListView) findViewById(R.id.paytypeshow_tv);
        go_pay = (TextView) findViewById(R.id.go_pay);
        back = (ImageView) findViewById(R.id.back);
        course_title = (TextView) findViewById(R.id.course_title);
        teacher_name = (TextView) findViewById(R.id.teacher_name);
        price_tv = (TextView) findViewById(R.id.price_tv);

        list = new ArrayList<>();

    }

    @Override
    protected void initData() {
        course_title.setText(resultClassBean.getClass_name());
        teacher_name.setText(getIntent().getStringExtra("teacher_name"));
        price_tv.setText("￥："+resultClassBean.getReal_price());

        channel = "";

        //
        bean = new TargetIdBean();
        bean.setValue0(resultClassBean.getClass_id());
        fetchPayType();
    }



    @Override
    protected void initEvent() {
        go_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (channel.equalsIgnoreCase("")){
                    Toast.makeText(PayActivity.this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveOrder();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayActivity.this.finish();
            }
        });

    }

    private void saveOrder() {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"order/generateorder", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAG-SaveOrder", "onResponse: " + response);
                Gson g = new Gson();
                OrderBean bean = g.fromJson(response, OrderBean.class);
                if (bean.getStatus() == 1) {

                    try {
                        Intent intent = new Intent(PayActivity.this, PaymentActivity.class);
                        intent.putExtra(PaymentActivity.EXTRA_CHARGE, g.toJson(bean.getData()) );
                        startActivityForResult(intent, REQUEST_CODE_PAYMENT);

                    } catch (Exception e) {
                        Toast.makeText(PayActivity.this, "支付失败！", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                Gson g = new Gson();
                if (ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("okey", Md5Utils.md5( ConstantSet.MD5Header+"generateOrder"+g.toJson(bean)+ConstantSet.user.getUid() )  );
                map.put("channel", channel);
                map.put("target_id", g.toJson(bean));
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }




    private void fetchPayType() {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"order/getpaychannel", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAG-PayType", response);
                Gson gson = new Gson();
                mPayTypeBean=  gson.fromJson(response, new TypeToken<PayTypeBean>() {
                }.getType());
                if (mPayTypeBean.getStatus()==1){
                    //设置list数据 和adapter
                    list.clear();
                    list = mPayTypeBean.getData();
                    adapter = new PayTypeListViewAdapter(PayActivity.this, list);

                    paytypeshow_tv.setAdapter(adapter);

                    paytypeshow_tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //获取选中的参数
                            selectPosition = position;
                            channel =list.get(position).getCode();
//                            Toast.makeText(PayActivity.this, "支付方式"+channel, Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                            
                        }
                    });

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("okey", Md5Utils.md5(ConstantSet.MD5Header+"payChannel") );
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    /**
     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
     * 最终支付成功根据异步通知为准
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                if ("success".equals(result)) {
                    Toast.makeText(PayActivity.this, "支付成功！", Toast.LENGTH_SHORT).show();
                    //跳转进课程
                    Intent i = new Intent(PayActivity.this, VideoActivity.class);
                    i.putExtra("class_id", resultClassBean.getClass_id());
                    PayActivity.this.startActivity(i);
                    PayActivity.this.finish();


                } else if ("fail".equals(result)) {
                    Toast.makeText(PayActivity.this, "支付失败！", Toast.LENGTH_SHORT).show();


                } else if ("cancel".equals(result)) {
                    Toast.makeText(PayActivity.this, "取消支付！", Toast.LENGTH_SHORT).show();


                } else if ("invalid".equals(result)) {

                    if (ConstantSet.CHANNEL_WECHAT.equals("wx")) //暂时写死wx
                    {
                        Toast.makeText(PayActivity.this, "未安装微信客户端！", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(PayActivity.this, "未安装付款插件！", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Toast.makeText(PayActivity.this, "支付失败！", Toast.LENGTH_SHORT).show();

                }
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                Log.i("TAG-PayResult","支付结果:===" + result + "|" + errorMsg + "|" + extraMsg);
            }
        }

    }



    public class PayTypeListViewAdapter extends BaseAdapter {

        private Context context;

        List<PayTypeBean.DataBean> beans;


        class ViewHolder {
            ImageView icon;
            TextView tvName;
            RadioButton cb;
            LinearLayout LL;
        }

        public PayTypeListViewAdapter(Context context, List<PayTypeBean.DataBean> beans) {
            // TODO Auto-generated constructor stub
            this.beans = beans;
            this.context = context;

        }



        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return beans.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return beans.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            // 页面
            Log.v("MyListViewBase", "getView " + position + " " + convertView);
            ViewHolder holder = null;

            LayoutInflater inflater = LayoutInflater.from(context);
            if (convertView == null) {
                convertView = inflater.inflate(
                        R.layout.paytype_item, null);
                holder = new ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                holder.cb = (RadioButton) convertView.findViewById(R.id.checkbox_check);
                holder.tvName = (TextView) convertView.findViewById(R.id.typename);
                holder.LL = (LinearLayout) convertView.findViewById(R.id.linear_layout_up);
                convertView.setTag(holder);
            } else {
                // 取出holder
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(beans.get(position).getTitle());
            LoadImgUtils.setImage(PayActivity.this, beans.get(position).getIcon(), holder.icon);

            if(selectPosition == position){
                holder.cb.setChecked(true);
                channel = list.get(position).getCode();
            }
            else{
                holder.cb.setChecked(false);

            }

            return convertView;
        }

    }


}
