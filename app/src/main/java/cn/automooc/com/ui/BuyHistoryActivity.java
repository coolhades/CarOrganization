package cn.automooc.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.BaseActivity;
import cn.automooc.com.R;
import cn.automooc.com.adapter.BuyHistoryListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.MyOrderListBean;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.Md5Utils;

public class BuyHistoryActivity extends BaseActivity {

    ListView buyHistoryListView;
    BuyHistoryListViewAdapter adapter;
    List<MyOrderListBean.DataBean.ListBean> lists;
    ImageView back_iv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_history);
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        back_iv = (ImageView) findViewById(R.id.back_iv);
        buyHistoryListView= (ListView) findViewById(R.id.buy_history_listview);
    }

    @Override
    protected void initData() {
        fetchOrder();
        lists=new ArrayList<>();
    }

    private void fetchOrder() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getuserorderlist?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.d("TAG-Order", response);
                Gson gson = new Gson();
                try {
                    MyOrderListBean result = gson.fromJson(response, MyOrderListBean.class);
                    if (result.getStatus() == 1) {
                        lists.clear();
                        lists = result.getData().getList();
                        adapter = new BuyHistoryListViewAdapter(lists, BuyHistoryActivity.this);

                        buyHistoryListView.setAdapter(adapter);

                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(BuyHistoryActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", ConstantSet.user.getUid());//后期改成course_id
                map.put("okey", Md5Utils.md5("moocgetUserOrderList" + ConstantSet.user.getUid() ));//设置班级
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

    @Override
    protected void initEvent() {
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyHistoryActivity.this.finish();
            }
        });

        buyHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lists.get(position).getStatus().equalsIgnoreCase("1")){
                    //已支付 进入class
                    Intent i = new Intent(BuyHistoryActivity.this, VideoActivity.class);
                    i.putExtra("class_id", lists.get(position).getTarget_id());
                    BuyHistoryActivity.this.startActivity(i);
                }else {
                    ConstantSet.course_id = lists.get(position).getImpower_id();
                    Intent i = new Intent(BuyHistoryActivity.this, CourseActivity.class);
                    BuyHistoryActivity.this.startActivity(i);
                }
            }
        });

    }
}
